package com.devsuperior.catalog.resources;

import com.devsuperior.catalog.dto.UserDTO;
import com.devsuperior.catalog.dto.UserInsertDTO;
import com.devsuperior.catalog.dto.UserUpdateDTO;
import com.devsuperior.catalog.resources.Exceptions.ValidationError;
import com.devsuperior.catalog.services.UserService;
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
@RequestMapping("/users")
public class UserResource {
  @Autowired
  private UserService service;

  @GetMapping
  @Operation(
    summary = "Users list",
    description = "Paginated list of Users",
    tags = { "Users" },
    responses = {
      @ApiResponse(
        description = "Success!",
        responseCode = "200",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = UserDTO.class)
        )
      ),
      @ApiResponse(
        description = "Error! Wrong formatted sorting criteria!",
        responseCode = "500",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(
            example = "{\"timestamp\":\"2022-07-18T18:06:27.690905796Z\",\"status\":500,\"error\":\"Internal Server Error\",\"path\":\"/users\"}"
          )
        )
      ),
    }
  )
  public ResponseEntity<Page<UserDTO>> findAll(
    @ParameterObject Pageable pageable
  ) {
    Page<UserDTO> list = service.findAllPaged(pageable);
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/{id}")
  @Operation(
    operationId = "findById",
    summary = "search User by identified number",
    description = "Receives the identifier and returns the corresponding User",
    tags = { "Users" },
    responses = {
      @ApiResponse(
        description = "Success! The User was found!",
        responseCode = "200",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = UserDTO.class)
        )
      ),
      @ApiResponse(
        description = "Error! Identifier number not found!",
        responseCode = "404",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(
            example = "{\"timestamp\":\"2022-07-18T18:06:27.690905796Z\",\"status\":404,\"error\":\"Resource not found\",\"message\":\"User not found\",\"path\":\"/users/{id}\"}"
          )
        )
      ),
    }
  )
  public ResponseEntity<UserDTO> findById(
    @Parameter(
      description = "User identifier number",
      required = true
    ) @PathVariable Long id
  ) {
    UserDTO dto = service.findById(id);
    return ResponseEntity.ok().body(dto);
  }

  @PostMapping
  @Operation(
    summary = "Insert a new User name",
    description = "Insert a new User name and return confirmation response",
    tags = { "Users" },
    responses = {
      @ApiResponse(
        description = "Success insert new User name",
        responseCode = "201",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = UserDTO.class)
        )
      ),
      @ApiResponse(
        description = "User field error",
        responseCode = "422",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = ValidationError.class)
        )
      ),
    }
  )
  public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserInsertDTO dto) {
    UserDTO newDto = service.insert(dto);
    URI uri = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(newDto.getId())
      .toUri();
    return ResponseEntity.created(uri).body(newDto);
  }

  @PutMapping(value = "/{id}")
  @Operation(
    summary = "Update the User",
    description = "Update the User and return the updated",
    tags = { "Users" },
    responses = {
      @ApiResponse(
        description = "Success updade User",
        responseCode = "200",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = UserDTO.class)
        )
      ),
      @ApiResponse(
        description = "User identifier not found",
        responseCode = "404",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(
            example = "{\"timestamp\":\"2022-07-18T18:06:27\",\"status\":404,\"error\":\"Resource not found\",\"message\":\"Id not found {id}\",\"path\":\"/users/{id}\"}"
          )
        )
      ),
      @ApiResponse(
        description = "User field error",
        responseCode = "422",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = ValidationError.class)
        )
      ),
    }
  )
  public ResponseEntity<UserDTO> update(
    @Parameter(description = "User identifier number") @PathVariable Long id,
    @Valid @RequestBody UserUpdateDTO dto
  ) {
    UserDTO newDto = service.update(id, dto);
    return ResponseEntity.ok().body(newDto);
  }

  @DeleteMapping(value = "/{id}")
  @Operation(
    summary = "Delete the User",
    description = "Delete the User and return the response",
    tags = { "Users" },
    responses = {
      @ApiResponse(
        description = "The User deleted successfully",
        responseCode = "204"
      ),
      @ApiResponse(
        description = "User identifier not found",
        responseCode = "404",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(
            example = "{\"timestamp\":\"2022-07-18T18:06:27\",\"status\":404,\"error\":\"Resource not found\",\"message\":\"Id not found {id}\",\"path\":\"/users/{id}\"}"
          )
        )
      ),
    }
  )
  public ResponseEntity<Void> delete(
    @Parameter(
      name = "id",
      description = "User identifier number",
      required = true
    ) @PathVariable Long id
  ) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
