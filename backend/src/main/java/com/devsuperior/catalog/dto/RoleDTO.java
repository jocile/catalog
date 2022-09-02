package com.devsuperior.catalog.dto;

import com.devsuperior.catalog.entities.Role;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String authority;

  public RoleDTO(Role role) {
    id = role.getId();
    authority = role.getAuthority();
  }
}
