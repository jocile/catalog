package com.devsuperior.catalog.resources.Exceptions;

import com.devsuperior.catalog.services.Exceptions.DatabaseException;
import com.devsuperior.catalog.services.Exceptions.ResourceNotFoundException;
import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<StandardError> handleEntityNotFound(
    ResourceNotFoundException e,
    HttpServletRequest request
  ) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    StandardError err = new StandardError();
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("Resource not found");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<StandardError> database(
    DatabaseException e,
    HttpServletRequest request
  ) {
    StandardError err = new StandardError();
    HttpStatus status = HttpStatus.BAD_REQUEST;
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("Database exception");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<StandardError> validation(
    MethodArgumentNotValidException e,
    HttpServletRequest request
  ) {
    StandardError err = new StandardError();
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; // 422 status code for invalid request
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("Validation exception");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }
}
