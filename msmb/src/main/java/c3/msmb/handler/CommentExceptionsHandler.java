package c3.msmb.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import c3.msmb.exceptions.comment.GetCommentsByPublicationIdException;
import c3.msmb.exceptions.comment.GetCommentsException;
import c3.msmb.exceptions.comment.SaveCommentException;

@RestControllerAdvice
public class CommentExceptionsHandler {
    @ExceptionHandler(GetCommentsException.class)
    public ResponseEntity<Map<String, Object>> getCommentsException(GetCommentsException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GetCommentsByPublicationIdException.class)
    public ResponseEntity<Map<String, Object>> getCommentsByPublicationIdException(GetCommentsByPublicationIdException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(SaveCommentException.class)
    public ResponseEntity<Map<String, Object>> saveCommentException(SaveCommentException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}