package com.devsuperior.catalog.services;

import com.devsuperior.catalog.entities.Category;
import com.devsuperior.catalog.repositories.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository repository;

  @Transactional(readOnly = true)
  public List<Category> findAll() {
    return repository.findAll();
  }
}
