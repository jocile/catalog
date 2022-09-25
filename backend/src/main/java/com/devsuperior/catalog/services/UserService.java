package com.devsuperior.catalog.services;

import com.devsuperior.catalog.dto.RoleDTO;
import com.devsuperior.catalog.dto.UserDTO;
import com.devsuperior.catalog.dto.UserInsertDTO;
import com.devsuperior.catalog.dto.UserUpdateDTO;
import com.devsuperior.catalog.entities.Role;
import com.devsuperior.catalog.entities.User;
import com.devsuperior.catalog.repositories.RoleRepository;
import com.devsuperior.catalog.repositories.UserRepository;
import com.devsuperior.catalog.services.Exceptions.DatabaseException;
import com.devsuperior.catalog.services.Exceptions.ResourceNotFoundException;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository repository;

  @Autowired
  RoleRepository roleRepository;

  @Transactional(readOnly = true)
  public Page<UserDTO> findAllPaged(Pageable pageable) {
    Page<User> list = repository.findAll(pageable);
    return list.map(obj -> new UserDTO(obj));
  }

  @Transactional(readOnly = true)
  public UserDTO findById(Long id) {
    Optional<User> obj = repository.findById(id);
    User entity = obj.orElseThrow(
      () -> new ResourceNotFoundException("User not found")
    );
    return new UserDTO(entity);
  }

  @Transactional
  public UserDTO insert(UserInsertDTO dto) {
    User entity = new User();
    copyDtoToEntity(dto, entity);
    entity.setPassword(passwordEncoder.encode(dto.getPassword()));
    entity = repository.save(entity);
    return new UserDTO(entity);
  }

  @Transactional
  public UserDTO update(Long id, UserUpdateDTO dto) {
    try {
      User entity = repository.getReferenceById(id);
      copyDtoToEntity(dto, entity);
      entity = repository.save(entity);
      return new UserDTO(entity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Id not found " + id);
    }
  }

  public void delete(Long id) {
    try {
      repository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException("Id not found " + id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Integrity violation");
    }
  }

  /**
   * This method receives the User object data to populate the User entity
   *
   * @param dto User data object
   * @param entity User
   */
  private void copyDtoToEntity(UserDTO dto, User entity) {
    entity.setFirstName(dto.getFirstName());
    entity.setLastName(dto.getLastName());
    entity.setEmail(dto.getEmail());

    entity.getRoles().clear();
    for (RoleDTO roleDto : dto.getRoles()) {
      Role role = roleRepository.getReferenceById(roleDto.getId());
      entity.getRoles().add(role);
    }
  }
}
