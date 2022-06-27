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
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {

        MessageResponse eDetails = this.generateResponse(e, HttpStatus.NOT_FOUND, "Resource not found.");
        return new ResponseEntity<>(eDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<?> handleServletException(ServletException e) {

        MessageResponse eDetails = this.generateResponse(e, HttpStatus.INTERNAL_SERVER_ERROR, "Difficulties found.");
        return new ResponseEntity<>(eDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<?> handlePropertyReferenceException(PropertyReferenceException e) {

        MessageResponse eDetails = this.generateResponse(e, HttpStatus.BAD_REQUEST, "Check request's information.");
        return new ResponseEntity<>(eDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {

        MessageResponse eDetails = this.generateResponse(e, HttpStatus.BAD_REQUEST, "Constraint violations.");
        return new ResponseEntity<>(eDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {

        MessageResponse eDetails = this.generateResponse(e, HttpStatus.BAD_REQUEST, "A method has been passed an illegal or inappropriate argument.");
        return new ResponseEntity<>(eDetails, HttpStatus.BAD_REQUEST);
    }

    public MessageResponse generateResponse(Exception e, HttpStatus status, String message) {
        return MessageResponse.MessageResponseBuilder
                .newBuilder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .title(message)
                .detail(e.getMessage())
                .message(e.getClass().getName())
                .build();
    }

}
