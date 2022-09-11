package com.devsuperior.catalog.dto;

import com.devsuperior.catalog.services.validation.UserInsertValid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@UserInsertValid
public class UserInsertDTO extends UserDTO {
  private static final long serialVersionUID = 1L;

  private String password;
}
