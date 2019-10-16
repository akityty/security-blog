package konkon.controller;

import konkon.model.Blog;
import konkon.model.BlogForm;
import konkon.model.Category;
import konkon.service.BlogService;
import konkon.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BlogController {
  @Autowired
  private BlogService blogService;
  @Autowired
  private CategoryService categoryService;

  @ModelAttribute("categories")
  public Iterable<Category> getAllCategory() {
    return categoryService.findAll();
  }

  @GetMapping("/blog/create")
  public ModelAndView showCreateForm() {
    ModelAndView modelAndView = new ModelAndView("/blog/create");
    modelAndView.addObject("blogForm", new BlogForm());
    return modelAndView;
  }

  @PostMapping("/blog/create")
  public ModelAndView saveBlog(@ModelAttribute BlogForm blogForm) {
    blogService.save(blogForm);
    return new ModelAndView("redirect:/blog/list");
  }

  @GetMapping("/blog/list")
  public ModelAndView showListBlog(@RequestParam("searchName") Optional<String> searchName, @PageableDefault(value = 3) Pageable pageable) {
    Page<Blog> blogs;
    if (searchName.isPresent()) {
      blogs = blogService.findAllByTittleContaining(searchName.get(), pageable);
    } else {
      blogs = blogService.findAll(pageable);
    }
    ModelAndView modelAndView = new ModelAndView("/blog/list");
    modelAndView.addObject("blogs", blogs);
    return modelAndView;
  }

  @GetMapping("/blog/edit/{id}")
  public ModelAndView showEditForm(@PathVariable Long id) {
    Blog blog = blogService.findById(id);
    if (blog != null) {
      ModelAndView modelAndView = new ModelAndView("/blog/edit");
      modelAndView.addObject("blog", blog);
      return modelAndView;
    } else {
      return new ModelAndView("/error");
    }
  }

  @PostMapping("/blog/edit")
  public ModelAndView editEmployee(@ModelAttribute BlogForm blogForm) {
    blogService.save(blogForm);
    return new ModelAndView("redirect:/blog/list");
  }

  @GetMapping("/blog/delete/{id}")
  public ModelAndView showDelete(@PathVariable Long id) {
    Blog blog = blogService.findById(id);
    if (blog != null) {
      ModelAndView modelAndView = new ModelAndView("/blog/delete");
      modelAndView.addObject("blog", blog);
      return modelAndView;
    } else {
      return new ModelAndView("/error");
    }
  }
  @PostMapping("/blog/delete")
  public ModelAndView modelAndView(@ModelAttribute BlogForm blogForm){
    blogService.delete(blogForm.getId());
    return new ModelAndView("redirect:/blog/list");
  }
  @GetMapping("/blog/view/{id}")
  public ModelAndView viewBlog(@PathVariable Long id){
    Blog blog = blogService.findById(id);
    ModelAndView modelAndView = new ModelAndView("/blog/view");
    modelAndView.addObject("blog",blog);
    return modelAndView;
  }

}
