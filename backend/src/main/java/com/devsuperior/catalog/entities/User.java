package com.devsuperior.catalog.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  @EqualsAndHashCode.Include
  private Long id;

  private String firstName;
  private String lastName;
  private String email;
  private String password;

  @Setter(value = AccessLevel.NONE)
  private Set<Role> roles = new HashSet<>();
}
