package konkon.service;

import konkon.model.Blog;
import konkon.model.BlogForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

public interface BlogService {
  void save(BlogForm blogForm);
  void delete(Long id);
  Blog getBlog(BlogForm blogForm);
  Blog findById(Long id);
  Page<Blog> findAll(Pageable pageable);
  Page<Blog> findAllByTittleContaining(String name,Pageable pageable);

}
