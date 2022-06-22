package com.biblioteca.exceptions;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException) {

        MessageResponse rnfDetails = MessageResponse.MessageResponseBuilder
                .newBuilder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found.")
                .detail(rfnException.getMessage())
                .message(rfnException.getClass().getName())
                .build();
        return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<?> handleServletException(ServletException e) {

        MessageResponse eDetails = MessageResponse.MessageResponseBuilder
                .newBuilder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .title("Difficulties found.")
                .detail(e.getMessage())
                .message(e.getClass().getName())
                .build();
        return new ResponseEntity<>(eDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<?> handlePropertyReferenceException(PropertyReferenceException e) {

        MessageResponse eDetails = MessageResponse.MessageResponseBuilder
                .newBuilder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Check request's information.")
                .detail(e.getMessage())
                .message(e.getClass().getName())
                .build();
        return new ResponseEntity<>(eDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {

        MessageResponse eDetails = MessageResponse.MessageResponseBuilder
                .newBuilder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Constraint violations.")
                .detail(e.getMessage())
                .message(e.getClass().getName())
                .build();
        return new ResponseEntity<>(eDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {

        MessageResponse eDetails = MessageResponse.MessageResponseBuilder
                .newBuilder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("A method has been passed an illegal or inappropriate argument.")
                .detail(e.getMessage())
                .message(e.getClass().getName())
                .build();
        return new ResponseEntity<>(eDetails, HttpStatus.BAD_REQUEST);
    }

}
