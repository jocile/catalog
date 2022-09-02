package com.devsuperior.catalog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserInsertDTO extends UserDTO {
  private static final long serialVersionUID = 1L;

  private String password;
}
