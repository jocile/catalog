package com.devsuperior.catalog.services;

import static org.mockito.Mockito.times;

import com.devsuperior.catalog.dto.ProductDTO;
import com.devsuperior.catalog.entities.Category;
import com.devsuperior.catalog.entities.Product;
import com.devsuperior.catalog.repositories.CategoryRepository;
import com.devsuperior.catalog.repositories.ProductRepository;
import com.devsuperior.catalog.services.Exceptions.DatabaseException;
import com.devsuperior.catalog.services.Exceptions.ResourceNotFoundException;
import com.devsuperior.catalog.tests.Factory;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {
  @InjectMocks
  private ProductService service;

  @Mock
  private ProductRepository repository;

  @Mock
  private CategoryRepository categoryRepository;

  private long existingId;
  private long nonExistingId;
  private long dependentId;
  private PageImpl<Product> page;
  private Product product;
  ProductDTO productDTO;
  private Category category;

  @BeforeEach
  void setUp() throws Exception {
    existingId = 1L;
    nonExistingId = 2L;
    dependentId = 3L;
    product = Factory.createProduct();
    productDTO = Factory.createProductDTO();
    category = Factory.createCategory();
    page = new PageImpl<>(List.of(product));

    Mockito
      .when(repository.findAll((Pageable) ArgumentMatchers.any()))
      .thenReturn(page);

    Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(product);

    Mockito
      .when(repository.findById(existingId))
      .thenReturn(Optional.of(product));
    Mockito
      .when(repository.findById(nonExistingId))
      .thenReturn(Optional.empty());

    Mockito.when(repository.getReferenceById(existingId)).thenReturn(product);

    Mockito
      .when(repository.getReferenceById(nonExistingId))
      .thenThrow(EntityNotFoundException.class);

    Mockito
      .when(categoryRepository.getReferenceById(existingId))
      .thenReturn(category);

    Mockito
      .when(categoryRepository.getReferenceById(nonExistingId))
      .thenThrow(EntityNotFoundException.class);

    Mockito.doNothing().when(repository).deleteById(existingId);
    Mockito
      .doThrow(EmptyResultDataAccessException.class)
      .when(repository)
      .deleteById(nonExistingId);
    Mockito
      .doThrow(DataIntegrityViolationException.class)
      .when(repository)
      .deleteById(dependentId);
  }

  @Test
  public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
    Assertions.assertThrows(
      ResourceNotFoundException.class,
      () -> {
        service.update(nonExistingId, productDTO);
      }
    );
  }

  @Test
  public void updateShouldReturnProductDTOWhenIdExists() {
    ProductDTO result = service.update(existingId, productDTO);

    Assertions.assertNotNull(result);
  }

  @Test
  public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
    Assertions.assertThrows(
      ResourceNotFoundException.class,
      () -> {
        service.findById(nonExistingId);
      }
    );
  }

  @Test
  public void findByIdShouldReturnProductDTOWhenIdExists() {
    ProductDTO result = service.findById(existingId);

    Assertions.assertNotNull(result);
  }

  @Test
  public void findAllPagedShouldReturnPage() {
    Pageable pageable = PageRequest.of(0, 12);

    Page<ProductDTO> result = service.findAllPaged(pageable);

    Assertions.assertNotNull(result);

    Mockito.verify(repository, times(1)).findAll(pageable);
  }

  @Test
  public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
    Assertions.assertThrows(
      DatabaseException.class,
      () -> {
        service.delete(dependentId);
      }
    );

    Mockito.verify(repository, Mockito.times(1)).deleteById(dependentId);
  }

  @Test
  public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
    Assertions.assertThrows(
      ResourceNotFoundException.class,
      () -> {
        service.delete(nonExistingId);
      }
    );

    Mockito.verify(repository, Mockito.times(1)).deleteById(nonExistingId);
  }

  @Test
  public void deleteShouldDoNothingWhenIdExists() {
    Assertions.assertDoesNotThrow(
      () -> {
        service.delete(existingId);
      }
    );

    Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
  }
}
