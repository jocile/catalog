package com.devsuperior.catalog.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
  private static final long serialVersionUID = 1L;
  private Long id;
  private String authority;
}
