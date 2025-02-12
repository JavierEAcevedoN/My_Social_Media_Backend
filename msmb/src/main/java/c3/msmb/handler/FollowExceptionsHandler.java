package c3.msmb.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import c3.msmb.exceptions.follow.FollowUserException;
import c3.msmb.exceptions.follow.GetFollowersException;
import c3.msmb.exceptions.follow.GetFollowingException;
import c3.msmb.exceptions.follow.GetFollowsException;
import c3.msmb.exceptions.follow.UnFollowUserException;

@RestControllerAdvice
public class FollowExceptionsHandler {
    @ExceptionHandler(GetFollowsException.class)
    public ResponseEntity<Map<String, Object>> followsNotFound(GetFollowsException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GetFollowersException.class)
    public ResponseEntity<Map<String, Object>> followersNotFound(GetFollowersException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GetFollowingException.class)
    public ResponseEntity<Map<String, Object>> followingNotFound(GetFollowingException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FollowUserException.class)
    public ResponseEntity<Map<String, Object>> followUserNotFound(FollowUserException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnFollowUserException.class)
    public ResponseEntity<Map<String, Object>> followUserNotFound(UnFollowUserException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}