package com.scmspain.tweet.infrastructure.exception;

import com.scmspain.tweet.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(final IllegalArgumentException ex,
        final WebRequest request) {
            return handleExceptionInternal(ex, buildResponseBody(ex), new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(final EntityNotFoundException ex, final WebRequest request) {
        return handleExceptionInternal(ex, buildResponseBody(ex), new HttpHeaders(), NOT_FOUND, request);
    }

    private Object buildResponseBody(RuntimeException ex) {
        return new Object() {
            public String message = ex.getMessage();
            public String exceptionClass = ex.getClass().getSimpleName();
        };
    }
}
