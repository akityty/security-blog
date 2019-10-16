package konkon.service.impl;

import konkon.model.Blog;
import konkon.model.Category;
import konkon.repository.BlogRepository;
import konkon.repository.CategoryRepository;
import konkon.service.BlogService;
import konkon.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
  @Autowired
  private BlogRepository blogRepository;

  @Autowired
  private CategoryRepository categoryRepository;
  @Override
  public Iterable<Category> findAll() {
    return categoryRepository.findAll();
  }

  @Override
  public Category findById(Long id) {
    return categoryRepository.findOne(id);
  }

  @Override
  public void save(Category category) {
    categoryRepository.save(category);
  }

  @Override
  public void delete(Long id) {
    Category category = findById(id);
    List<Blog> blogs = (List<Blog>) findAllByCategory(category);
    for (Blog blog : blogs) {
      blogRepository.delete(blog.getId());
    }
    categoryRepository.delete(id);
  }

  @Override
  public Iterable<Blog> findAllByCategory(Category category) {
    return blogRepository.findAllByCategory(category);
  }
}
