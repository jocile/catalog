package com.devsuperior.catalog.dto;

import com.devsuperior.catalog.entities.User;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;

  private String firstName;
  private String lastName;
  private String email;

  @Setter(value = AccessLevel.NONE)
  Set<RoleDTO> roles = new HashSet<>();

  public UserDTO(User entity) {
    id = entity.getId();
    firstName = entity.getFirstName();
    lastName = entity.getLastName();
    email = entity.getEmail();
  }
}
