package konkon.service;

import konkon.model.Blog;
import konkon.model.Category;

public interface CategoryService {
  Iterable<Category> findAll();
  Category findById(Long id);
  void save(Category category);
  void delete(Long id);
  Iterable<Blog> findAllByCategory(Category category);
}
