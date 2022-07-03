package com.devsuperior.catalog.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;

  @EqualsAndHashCode.Exclude
  private String name;
}
