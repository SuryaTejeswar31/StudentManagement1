package org.example.studentmanagementsecure.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException{
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ExceptionDetails> handleDuplicateEmailException(DuplicateEmailException ex, HttpServletRequest request){
        ExceptionDetails exceptionDetails=new ExceptionDetails(LocalDateTime.now(),HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(), ex.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDetails);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request){
        ExceptionDetails exceptionDetails=new ExceptionDetails(LocalDateTime.now(),HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDetails);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidExceptions> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request){
        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error)->{errors.put(error.getField(),error.getDefaultMessage());});
        ValidExceptions exceptions=new ValidExceptions(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage(),request.getRequestURI(),errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptions);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionDetails> handleAuthenticationException(AuthenticationException ex, HttpServletRequest request){
        ExceptionDetails exceptionDetails=new ExceptionDetails(LocalDateTime.now(),HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), ex.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exceptionDetails);
    }
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ExceptionDetails> handleForbiddenException(ForbiddenException ex, HttpServletRequest request){
        ExceptionDetails exceptionDetails=new ExceptionDetails(LocalDateTime.now(),HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), ex.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionDetails);
    }
}
