package com.devsuperior.catalog.services;

import com.devsuperior.catalog.dto.CategoryDTO;
import com.devsuperior.catalog.dto.ProductDTO;
import com.devsuperior.catalog.entities.Category;
import com.devsuperior.catalog.entities.Product;
import com.devsuperior.catalog.repositories.CategoryRepository;
import com.devsuperior.catalog.repositories.ProductRepository;
import com.devsuperior.catalog.services.Exceptions.DatabaseException;
import com.devsuperior.catalog.services.Exceptions.ResourceNotFoundException;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
  @Autowired
  private ProductRepository repository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Transactional(readOnly = true)
  public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
    Page<Product> list = repository.findAll(pageRequest);
    return list.map(obj -> new ProductDTO(obj));
  }

  @Transactional(readOnly = true)
  public ProductDTO findById(Long id) {
    Optional<Product> obj = repository.findById(id);
    Product entity = obj.orElseThrow(
      () -> new ResourceNotFoundException("Product not found")
    );
    return new ProductDTO(entity, entity.getCategories());
  }

  @Transactional
  public ProductDTO insert(ProductDTO dto) {
    Product entity = new Product();
    copyDtoToEntity(dto, entity);
    entity = repository.save(entity);
    return new ProductDTO(entity);
  }

  @Transactional
  public ProductDTO update(Long id, ProductDTO dto) {
    try {
      Product entity = repository.getReferenceById(id);
      copyDtoToEntity(dto, entity);
      entity = repository.save(entity);
      return new ProductDTO(entity);
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
   * This method receives the product object data to populate the product entity
   *
   * @param dto Product data object
   * @param entity product
   */
  private void copyDtoToEntity(ProductDTO dto, Product entity) {
    entity.setName(dto.getName());
    entity.setDescription(dto.getDescription());
    entity.setDate(dto.getDate());
    entity.setImgUrl(dto.getImgUrl());
    entity.setPrice(dto.getPrice());
    entity.getCategories().clear();
    for (CategoryDTO catDto : dto.getCategories()) {
      Category category = categoryRepository.getReferenceById(catDto.getId());
      entity.getCategories().add(category);
    }
  }
}
