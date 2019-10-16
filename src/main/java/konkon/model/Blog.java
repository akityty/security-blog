package konkon.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "blogs")
public class Blog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private LocalDate createDate;
  private String tittle;
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
  private String content;
  private String image;

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

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Blog(LocalDate createDate, String tittle, Category category, String content, String image) {
    this.createDate = createDate;
    this.tittle = tittle;
    this.category = category;
    this.content = content;
    this.image = image;
  }

  public Blog(Long id, LocalDate createDate, String tittle, Category category, String content, String image) {
    this.id = id;
    this.createDate = createDate;
    this.tittle = tittle;
    this.category = category;
    this.content = content;
    this.image = image;
  }

  public Blog() {
  }
}
