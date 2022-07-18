package com.devsuperior.catalog.resources;

import com.devsuperior.catalog.dto.CategoryDTO;
import com.devsuperior.catalog.entities.Category;
import com.devsuperior.catalog.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping(value = "/{id}")
  @Operation(
    operationId = "findById",
    summary = "search category by identified number",
    description = "Receives the identifier and returns the corresponding category",
    tags = { "categories" },
    responses = {
      @ApiResponse(
        description = "Success! The category was found!",
        responseCode = "200",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = Category.class)
        )
      ),
      @ApiResponse(
        description = "Error! Identifier number not found!",
        responseCode = "404",
        content = @Content
      ),
    }
  )
  public ResponseEntity<CategoryDTO> findById(
    @Parameter(
      description = "Category identifier number",
      required = true
    ) @PathVariable Long id
  ) {
    CategoryDTO dto = service.findById(id);
    return ResponseEntity.ok().body(dto);
  }
}
