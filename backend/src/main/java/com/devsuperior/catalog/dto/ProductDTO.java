package com.devsuperior.catalog.dto;

import com.devsuperior.catalog.entities.Category;
import com.devsuperior.catalog.entities.Product;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;

  @Size(min = 3, max = 60, message = "Must be between 3 and 60 characters")
  @NotBlank(message = "Required field")
  private String name;

  @NotBlank(message = "Required field")
  private String description;

  @Positive(message = "The price must be a positive value")
  private Double price;

  private String imgUrl;

  @PastOrPresent(message = "The date cannot be future")
  private Instant date;

  private List<CategoryDTO> categories = new ArrayList<>();

  public ProductDTO(
    Long id,
    String name,
    String description,
    Double price,
    String imgUrl,
    Instant date
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.imgUrl = imgUrl;
    this.date = date;
  }

  public ProductDTO(Product entity) {
    this.id = entity.getId();
    this.name = entity.getName();
    this.description = entity.getDescription();
    this.price = entity.getPrice();
    this.imgUrl = entity.getImgUrl();
    this.date = entity.getDate();
  }

  public ProductDTO(Product entity, Set<Category> categories) {
    this(entity);
    categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
  }
}
