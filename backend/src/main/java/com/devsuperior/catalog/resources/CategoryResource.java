package com.devsuperior.catalog.resources;

import com.devsuperior.catalog.entities.Category;
import io.swagger.v3.oas.annotations.Operation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

  @GetMapping
  @Operation(
    summary = "Categories list",
    description = "List of all categories",
    tags = { "Categories" }
  )
  public ResponseEntity<List<Category>> findAll() {
    List<Category> list = new ArrayList<>();
    list.add(new Category(1L, "Books"));
    list.add(new Category(2L, "Electronics"));
    return ResponseEntity.ok().body(list);
  }
}
