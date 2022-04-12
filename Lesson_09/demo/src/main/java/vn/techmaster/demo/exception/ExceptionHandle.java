package vn.techmaster.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class ExceptionHandle {
    // Xử lý riêng BadRequestException
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException exception){
        ErrorResponse err = new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }

    // Xử lý chung với các Exception còn lại
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleOrtherException(RuntimeException exception){
        ErrorResponse err = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }
}
