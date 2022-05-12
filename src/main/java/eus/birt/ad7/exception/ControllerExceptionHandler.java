package eus.birt.ad7.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> resourceNotFoundException(ResourceNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorDetail(e.getClass().getSimpleName(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetail> argumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Validation error in request", e);
        return new ResponseEntity<>(new ErrorDetail(e.getClass().getSimpleName(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErrorDetail> duplicateKeyException(DuplicateKeyException e) {
        log.error("Unique id constraint violation", e);
        return new ResponseEntity<>(new ErrorDetail(e.getClass().getSimpleName(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @Data
    @AllArgsConstructor
    private static class ErrorDetail {
        private String error;
        private String message;
    }
}
