package c3.msmb.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import c3.msmb.exceptions.user.GetByUsernameException;
import c3.msmb.exceptions.user.GetUsersException;
import c3.msmb.exceptions.user.PatchUserException;
import c3.msmb.exceptions.user.SaveUserException;

@RestControllerAdvice
public class UserExceptionsHandler {
    @ExceptionHandler(GetByUsernameException.class)
    public ResponseEntity<Map<String,Object>> usernameNotFound(GetByUsernameException exc) {
        Map<String,Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GetUsersException.class)
    public ResponseEntity<Map<String,Object>> usersNotFound(GetUsersException exc) {
        Map<String,Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SaveUserException.class)
    public ResponseEntity<Map<String,Object>> saveUserIncomplete(SaveUserException exc) {
        Map<String,Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PatchUserException.class)
    public ResponseEntity<Map<String,Object>> userPatchNotFound(PatchUserException exc) {
        Map<String,Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}