package konkon.controller;

import konkon.model.Blog;
import konkon.model.Category;
import konkon.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class CategoryController {
  @Autowired
 private CategoryService categoryService;

  @GetMapping("/category/create")
  public ModelAndView showCreateForm() {
    ModelAndView modelAndView = new ModelAndView("/category/create");
    modelAndView.addObject("category", new Category());
    return modelAndView;
  }

  @PostMapping("/category/create")
  public ModelAndView saveCategory(@ModelAttribute Category category) {
    categoryService.save(category);
    ModelAndView modelAndView = new ModelAndView("/category/list");
    modelAndView.addObject(category);
    return modelAndView;
  }
  @GetMapping("/category/list")
  public ModelAndView categoryList(){
    Iterable<Category> categories = categoryService.findAll();
    ModelAndView modelAndView = new ModelAndView("/category/list");
    modelAndView.addObject("categories", categories);
    return modelAndView;
  }
  @GetMapping("/category/edit/{id}")
  public ModelAndView showDeleteCategory(@PathVariable Long id){
    Category category =  categoryService.findById(id);
    if(category == null){
      return new ModelAndView("/error");
    }
    ModelAndView modelAndView = new ModelAndView("/category/edit");
    modelAndView.addObject(category);
    return modelAndView;
  }
  @PostMapping("/category/edit")
  public ModelAndView saveCategory(@ModelAttribute Category category, BindingResult result){
    categoryService.save(category);
    return new ModelAndView("redirect:/category/list");
  }
  @GetMapping("/category/delete/{id}")
  public ModelAndView showDelete(@PathVariable Long id){
    Category category = categoryService.findById(id);
    if(category == null){
      return new ModelAndView("/error");
    }
    ModelAndView modelAndView = new ModelAndView("/category/delete");
    modelAndView.addObject(category);
    return modelAndView;
  }
  @PostMapping("/category/delete")
  public ModelAndView deleteCategory(@ModelAttribute Category category){
    Long i = category.getId();
    categoryService.delete(category.getId());
    return new ModelAndView("redirect:/category/list");
  }
  @GetMapping("/category/view/{id}")
  public ModelAndView showBlogsByCategory( Category  category){
    Iterable<Blog> blogs = categoryService.findAllByCategory(category);
    ModelAndView modelAndView = new ModelAndView("/category/view");
    modelAndView.addObject("blogs",blogs);
    modelAndView.addObject("category",category);
    return modelAndView;
  }
  @PostMapping("/category/view")
  public ModelAndView modelAndView(@ModelAttribute Category category){
    ModelAndView modelAndView = new ModelAndView("/category/view");
    modelAndView.addObject(category);
    return modelAndView;
  }
}
