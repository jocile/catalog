package com.devsuperior.catalog.resources;

import com.devsuperior.catalog.dto.ProductDTO;
import com.devsuperior.catalog.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.net.URI;
import javax.validation.Valid;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/products")
public class ProductResource {
  @Autowired
  private ProductService service;

  @GetMapping
  @Operation(
    summary = "Products list",
    description = "Paginated list of Products",
    tags = { "Products" },
    responses = {
      @ApiResponse(
        description = "Success!",
        responseCode = "200",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = ProductDTO.class)
        )
      ),
      @ApiResponse(
        description = "Error! Wrong formatted sorting criteriav!",
        responseCode = "500",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(
            example = "{\"timestamp\":\"2022-07-18T18:06:27.690905796Z\",\"status\":500,\"error\":\"Internal Server Error\",\"path\":\"/Products\"}"
          )
        )
      ),
    }
  )
  public ResponseEntity<Page<ProductDTO>> findAll(
    @ParameterObject Pageable pageable
  ) {
    Page<ProductDTO> list = service.findAllPaged(pageable);
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/{id}")
  @Operation(
    operationId = "findById",
    summary = "search Product by identified number",
    description = "Receives the identifier and returns the corresponding Product",
    tags = { "Products" },
    responses = {
      @ApiResponse(
        description = "Success! The Product was found!",
        responseCode = "200",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = ProductDTO.class)
        )
      ),
      @ApiResponse(
        description = "Error! Identifier number not found!",
        responseCode = "404",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(
            example = "{\"timestamp\":\"2022-07-18T18:06:27.690905796Z\",\"status\":404,\"error\":\"Resource not found\",\"message\":\"Product not found\",\"path\":\"/Products/{id}\"}"
          )
        )
      ),
    }
  )
  public ResponseEntity<ProductDTO> findById(
    @Parameter(
      description = "Product identifier number",
      required = true
    ) @PathVariable Long id
  ) {
    ProductDTO dto = service.findById(id);
    return ResponseEntity.ok().body(dto);
  }

  @PostMapping
  @Operation(
    summary = "Insert a new Product name",
    description = "Insert a new Product name and return confirmation response",
    tags = { "Products" },
    responses = {
      @ApiResponse(
        description = "Success insert new Product name",
        responseCode = "201",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = ProductDTO.class)
        )
      ),
      @ApiResponse(
        description = "Product name error",
        responseCode = "422",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(
            example = "{\"timestamp\":\"2022-07-18T18:06:27\",\"status\":422,\"error\":\"Validation exception\",\"message\":\"Validation failed for argument\",\"path\":\"/Products\"}"
          )
        )
      ),
    }
  )
  public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
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
    summary = "Update the Product",
    description = "Update the Product and return the updated",
    tags = { "Products" },
    responses = {
      @ApiResponse(
        description = "Success updade Product",
        responseCode = "200",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = ProductDTO.class)
        )
      ),
      @ApiResponse(
        description = "Product identifier not found",
        responseCode = "404",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(
            example = "{\"timestamp\":\"2022-07-18T18:06:27\",\"status\":404,\"error\":\"Resource not found\",\"message\":\"Id not found {id}\",\"path\":\"/Products/{id}\"}"
          )
        )
      ),
    }
  )
  public ResponseEntity<ProductDTO> update(
    @Parameter(description = "Product identifier number") @PathVariable Long id,
    @Valid @RequestBody ProductDTO dto
  ) {
    dto = service.update(id, dto);
    return ResponseEntity.ok().body(dto);
  }

  @DeleteMapping(value = "/{id}")
  @Operation(
    summary = "Delete the Product",
    description = "Delete the Product and return the response",
    tags = { "Products" },
    responses = {
      @ApiResponse(
        description = "The Product deleted successfully",
        responseCode = "204"
      ),
      @ApiResponse(
        description = "Product identifier not found",
        responseCode = "404",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(
            example = "{\"timestamp\":\"2022-07-18T18:06:27\",\"status\":404,\"error\":\"Resource not found\",\"message\":\"Id not found {id}\",\"path\":\"/Products/{id}\"}"
          )
        )
      ),
    }
  )
  public ResponseEntity<Void> delete(
    @Parameter(
      name = "id",
      description = "Product identifier number",
      required = true
    ) @PathVariable Long id
  ) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
