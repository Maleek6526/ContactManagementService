package cohort22.ByteBuilder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
        System.out.println("Handling UserNotFoundException: " + ex.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> handleIllegalStateException(IllegalStateException ex) {
        System.out.println("Handling IllegalStateException: " + ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        System.out.println("Handling IllegalArgumentException: " + ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex);
    }

    private ResponseEntity<Map<String, String>> buildErrorResponse(HttpStatus status, Exception ex) {
        Map<String, String> response = new HashMap<>();
        String errorMessage = ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred";
        response.put("error", errorMessage);
        return ResponseEntity.status(status).body(response);
    }
}

