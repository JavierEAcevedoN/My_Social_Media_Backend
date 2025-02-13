package c3.msmb.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import c3.msmb.exceptions.publication.DeleteByPublicationIdException;
import c3.msmb.exceptions.publication.GetPublicationsException;
import c3.msmb.exceptions.publication.PublicationByIdException;
import c3.msmb.exceptions.publication.PublicationsByUsernameException;
import c3.msmb.exceptions.publication.SaveByPublicationIdException;
import c3.msmb.exceptions.publication.UpdatePublicationException;

@RestControllerAdvice
public class PublicationExceptionsHandler {
    @ExceptionHandler(UpdatePublicationException.class)
    public ResponseEntity<Map<String, Object>> updatePublicationIdNotFound(UpdatePublicationException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SaveByPublicationIdException.class)
    public ResponseEntity<Map<String, Object>> usernameNotFound(SaveByPublicationIdException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DeleteByPublicationIdException.class)
    public ResponseEntity<Map<String, Object>> publicationDeleteNotFound(DeleteByPublicationIdException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GetPublicationsException.class)
    public ResponseEntity<Map<String, Object>> publicationsNotFound(GetPublicationsException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PublicationByIdException.class)
    public ResponseEntity<Map<String, Object>> publicationByIdNotFound(PublicationByIdException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PublicationsByUsernameException.class)
    public ResponseEntity<Map<String, Object>> publicatiResponseEntitysByUsername(PublicationsByUsernameException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}