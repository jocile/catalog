package com.devsuperior.catalog.resources;

import com.devsuperior.catalog.dto.CategoryDTO;
import com.devsuperior.catalog.entities.Category;
import com.devsuperior.catalog.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    tags = { "Categories" },
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
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(
            example = "{\"timestamp\":\"2022-07-18T18:06:27.690905796Z\",\"status\":404,\"error\":\"Resourcenotfound\",\"message\":\"Category not found\",\"path\":\"/categories/{id}\"}"
          )
        )
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

  @PostMapping
  @Operation(
    summary = "Insert a new category name",
    description = "Insert a new category name and return confirmation response",
    tags = { "Categories" },
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(example = "{\"name\":\"Category name\"}")
      )
    ),
    responses = {
      @ApiResponse(
        description = "Success insert new category name",
        responseCode = "201",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = CategoryDTO.class)
        )
      ),
      @ApiResponse(
        description = "Category name error",
        responseCode = "400",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(
            example = "{\"timestamp\":\"2022-07-18T18:06:27\",\"status\":400,\"error\":\"Bad Request\",\"path\":\"/categories\"}"
          )
        )
      ),
    }
  )
  public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto) {
    dto = service.insert(dto);
    URI uri = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(dto.getId())
      .toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @PutMapping(value = "/{id}")
  @Operation(
    summary = "Update the category",
    description = "Update the category and return the updated",
    tags = { "Categories" },
    responses = {
      @ApiResponse(
        description = "Success updade category",
        responseCode = "200",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = Category.class)
        )
      ),
      @ApiResponse(
        description = "Category identifier not found",
        responseCode = "404",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(
            example = "{\"timestamp\":\"2022-07-18T18:06:27\",\"status\":404,\"error\":\"Resource not found\",\"message\":\"Id not found {id}\",\"path\":\"/categories/{id}\"}"
          )
        )
      ),
    }
  )
  public ResponseEntity<CategoryDTO> update(
    @Parameter(
      name = "id",
      description = "Category identifier number",
      required = true
    ) @PathVariable Long id,
    @RequestBody CategoryDTO dto
  ) {
    dto = service.update(id, dto);
    return ResponseEntity.ok().body(dto);
  }

  @DeleteMapping(value = "/{id}")
  @Operation(
    summary = "Delete the category",
    description = "Delete the category and return the response",
    tags = { "Categories" },
    responses = {
      @ApiResponse(
        description = "The category deleted successfully",
        responseCode = "204"
      ),
      @ApiResponse(
        description = "Category identifier not found",
        responseCode = "404",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(
            example = "{\"timestamp\":\"2022-07-18T18:06:27\",\"status\":404,\"error\":\"Resource not found\",\"message\":\"Id not found {id}\",\"path\":\"/categories/{id}\"}"
          )
        )
      ),
    }
  )
  public ResponseEntity<Void> delete(
    @Parameter(
      name = "id",
      description = "Category identifier number",
      required = true
    ) @PathVariable Long id
  ) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
