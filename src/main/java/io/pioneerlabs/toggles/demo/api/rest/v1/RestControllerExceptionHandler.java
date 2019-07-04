package io.pioneerlabs.toggles.demo.api.rest.v1;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class,
            IllegalStateException.class })
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (ex instanceof IllegalArgumentException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof IllegalStateException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), status, request);
    }
}
