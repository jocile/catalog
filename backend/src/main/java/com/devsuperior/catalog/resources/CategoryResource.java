package com.devsuperior.catalog.resources;

import com.devsuperior.catalog.dto.CategoryDTO;
import com.devsuperior.catalog.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
  @Autowired
  private CategoryService service;

  @GetMapping
  @Operation(
    summary = "Categories list",
    description = "List of all categories",
    tags = { "Categories" }
  )
  public ResponseEntity<List<CategoryDTO>> findAll() {
    List<CategoryDTO> list = service.findAll();
    return ResponseEntity.ok().body(list);
  }
}
