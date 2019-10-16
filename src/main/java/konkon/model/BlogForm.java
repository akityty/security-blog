package konkon.model;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class BlogForm {
  private Long id;
  private LocalDate createDate;
  private String tittle;
  private String content;
  private MultipartFile image;
  private Category category;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDate createDate) {
    this.createDate = createDate;
  }

  public String getTittle() {
    return tittle;
  }

  public void setTittle(String tittle) {
    this.tittle = tittle;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public MultipartFile getImage() {
    return image;
  }

  public void setImage(MultipartFile image) {
    this.image = image;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public BlogForm(Long id, LocalDate createDate, String tittle, String content, MultipartFile image, Category category) {
    this.id = id;
    this.createDate = createDate;
    this.tittle = tittle;
    this.content = content;
    this.image = image;
    this.category = category;
  }

  public BlogForm() {
  }
}
