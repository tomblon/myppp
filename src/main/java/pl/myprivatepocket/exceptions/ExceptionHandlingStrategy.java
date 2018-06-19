package pl.myprivatepocket.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.myprivatepocket.models.dto.ErrorInfoDto;

import java.io.IOException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionHandlingStrategy {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> noSuchElementException(ConstraintViolationException ex) {
        return new ResponseEntity<>(new ErrorInfoDto(ex.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> authenticationException(AuthenticationException ex) {
        return new ResponseEntity<>(new ErrorInfoDto(ex.getMessage()), UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException ex) {
        return new ResponseEntity<>(new ErrorInfoDto(ex.getMessage()), NOT_FOUND);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> ioException(IOException ex) {
        return new ResponseEntity<>(new ErrorInfoDto(ex.getMessage()), BAD_REQUEST);
    }
}
