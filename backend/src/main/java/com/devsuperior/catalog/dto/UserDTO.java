package com.devsuperior.catalog.dto;

import com.devsuperior.catalog.entities.User;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

  @NotBlank(message = "Required field")
  private String firstName;

  private String lastName;

  @Email(message = "Valid email required")
  private String email;

  @Setter(value = AccessLevel.NONE)
  Set<RoleDTO> roles = new HashSet<>();

  public UserDTO(User entity) {
    id = entity.getId();
    firstName = entity.getFirstName();
    lastName = entity.getLastName();
    email = entity.getEmail();
    entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
  }
}
