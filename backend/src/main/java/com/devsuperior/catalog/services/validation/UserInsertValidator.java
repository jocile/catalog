package com.devsuperior.catalog.services.validation;

import com.devsuperior.catalog.dto.UserInsertDTO;
import com.devsuperior.catalog.entities.User;
import com.devsuperior.catalog.repositories.UserRepository;
import com.devsuperior.catalog.resources.Exceptions.FieldMessage;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UserInsertValidator
  implements ConstraintValidator<UserInsertValid, UserInsertDTO> {
  @Autowired
  private UserRepository repository;

  @Override
  public void initialize(UserInsertValid ann) {}

  @Override
  public boolean isValid(
    UserInsertDTO dto,
    ConstraintValidatorContext context
  ) {
    List<FieldMessage> list = new ArrayList<>();

    User user = repository.findByEmail(dto.getEmail());
    if (user != null) {
      list.add(new FieldMessage("Email", "Email already exists"));
    }

    for (FieldMessage e : list) {
      context.disableDefaultConstraintViolation();
      context
        .buildConstraintViolationWithTemplate(e.getMessage())
        .addPropertyNode(e.getFieldName())
        .addConstraintViolation();
    }
    return list.isEmpty();
  }
}
