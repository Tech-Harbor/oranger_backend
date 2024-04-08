package com.example.backend.web.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

import static com.example.backend.utils.Constants.PATH;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ErrorResponseDTO> handleNotFoundException(final RuntimeException ex,
                                                                       final WebRequest request) {

        String message = ex.getMessage().replaceAll(PATH, "");

        String path = request.getDescription(false).replaceAll("uri=", "");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.name(),
                HttpStatus.NOT_FOUND.value(),
                message,
                path));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatusCode status,
                                                                  final WebRequest request) {

        String message = ex.getMessage().replaceAll(PATH, "");

        String path = request.getDescription(false).replaceAll("uri=", "");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.name(),
                HttpStatus.BAD_REQUEST.value(),
                message,
                path));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatusCode status,
                                                                  final WebRequest request) {

        String message = ex.getMessage().replaceAll(PATH, "");

        String path = request.getDescription(false).replaceAll("uri=", "");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.name(),
                HttpStatus.BAD_REQUEST.value(),
                message,
                path));
    }
}
