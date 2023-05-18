package com.team600.moalarm.auth.common.exception.handler;

import com.team600.moalarm.auth.common.exception.BadRequestException;
import com.team600.moalarm.auth.common.exception.ConflictException;
import com.team600.moalarm.auth.common.exception.NotFoundException;
import javax.validation.ConstraintViolationException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorBody> handleBadRequest(BadRequestException e) {
        return ResponseEntity.badRequest().body(ErrorBody.of(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorBody> handleNotFound(NotFoundException e) {
        return new ResponseEntity<>(ErrorBody.of(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorBody> handleConflict(ConflictException e) {
        return new ResponseEntity<>(ErrorBody.of(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorBody> handleValidation(ConstraintViolationException e) {
        return new ResponseEntity<>(ErrorBody.of(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Getter
    static class ErrorBody {

        private final String message;

        private ErrorBody(String message) {
            this.message = message;
        }

        static ErrorBody of(String message) {
            return new ErrorBody(message);
        }
    }
}
