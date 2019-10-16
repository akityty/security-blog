package konkon.service.impl;

import konkon.model.Blog;
import konkon.model.BlogForm;
import konkon.model.Category;
import konkon.repository.BlogRepository;
import konkon.repository.CategoryRepository;
import konkon.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@PropertySource("classpath:global_config_app.properties")
public class BlogServiceImpl implements BlogService {
  @Autowired
  Environment env;
  @Autowired
  private BlogRepository blogRepository;
  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public void save(BlogForm blogForm) {
    Blog blog = getBlog(blogForm);
    blogRepository.save(blog);
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public Blog getBlog(BlogForm blogForm) {
    MultipartFile multipartFile = blogForm.getImage();
    String fileName = multipartFile.getOriginalFilename();
    String fileUpload = env.getProperty("file_upload").toString();

    try {
      //multipartFile.transferTo(imageFile);
      FileCopyUtils.copy(blogForm.getImage().getBytes(), new File(fileUpload + fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (fileName.equals("") && blogForm.getId() != null) {
      Blog blog = findById(blogForm.getId());
      fileName = blog.getImage();
    }
    if (blogForm.getId() == null) {
      return new Blog(blogForm.getCreateDate(), blogForm.getTittle(), blogForm.getCategory(), blogForm.getContent(), fileName);
    } else
      return new Blog(blogForm.getId(), blogForm.getCreateDate(), blogForm.getTittle(), blogForm.getCategory(), blogForm.getContent(),fileName);
  }

  @Override
  public Blog findById(Long id) {
    return blogRepository.findOne(id);
  }

  @Override
  public Page<Blog> findAll(Pageable pageable) {
    return blogRepository.sort(pageable) ;
  }

  @Override
  public Page<Blog> findAllByTittleContaining(String name, Pageable pageable) {
    return blogRepository.findAllByTittleContaining(name,pageable);
  }
}
