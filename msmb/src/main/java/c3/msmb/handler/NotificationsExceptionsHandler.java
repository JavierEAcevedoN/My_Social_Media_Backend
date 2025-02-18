package c3.msmb.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import c3.msmb.exceptions.notification.ByUsernameAndReadedException;
import c3.msmb.exceptions.notification.ByUsernameException;
import c3.msmb.exceptions.notification.GetNotificationsException;
import c3.msmb.exceptions.notification.SaveNotificationException;
import c3.msmb.exceptions.notification.MarkAsReadException;

@RestControllerAdvice
public class NotificationsExceptionsHandler {
    @ExceptionHandler(GetNotificationsException.class)
    public ResponseEntity<Map<String, Object>> notificationsNotFound(GetNotificationsException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ByUsernameException.class)
    public ResponseEntity<Map<String, Object>> notificationsByUsernameNotContent(ByUsernameException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ByUsernameAndReadedException.class)
    public ResponseEntity<Map<String, Object>> notificationsByUsernameAndReadedNotFound(ByUsernameAndReadedException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SaveNotificationException.class)
    public ResponseEntity<Map<String, Object>> saveNotificationByUsername(SaveNotificationException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MarkAsReadException.class)
    public ResponseEntity<Map<String, Object>> markAsReadException(MarkAsReadException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}