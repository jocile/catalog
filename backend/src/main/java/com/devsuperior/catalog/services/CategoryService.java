package com.devsuperior.catalog.services;

import com.devsuperior.catalog.dto.CategoryDTO;
import com.devsuperior.catalog.entities.Category;
import com.devsuperior.catalog.repositories.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository repository;

  @Transactional(readOnly = true)
  public List<CategoryDTO> findAll() {
    List<Category> list = repository.findAll();

    return list
      .stream()
      .map(obj -> new CategoryDTO(obj))
      .collect(Collectors.toList());
  }
}
