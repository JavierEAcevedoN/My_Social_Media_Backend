package c3.msmb.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import c3.msmb.exceptions.like.ByPublicationId;
import c3.msmb.exceptions.like.GetLikesException;
import c3.msmb.exceptions.like.LikePublicationException;
import c3.msmb.exceptions.like.UnLikePublicationException;

@RestControllerAdvice
public class LikeExceptionsHandler {
    @ExceptionHandler(GetLikesException.class)
    public ResponseEntity<Map<String, Object>> likesNotFound(GetLikesException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ByPublicationId.class)
    public ResponseEntity<Map<String, Object>> likesNotFoundByPublicationId(ByPublicationId exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LikePublicationException.class)
    public ResponseEntity<Map<String, Object>> likePublication(LikePublicationException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnLikePublicationException.class)
    public ResponseEntity<Map<String, Object>> unLikePublication(UnLikePublicationException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}