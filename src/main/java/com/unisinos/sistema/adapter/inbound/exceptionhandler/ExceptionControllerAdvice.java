package com.unisinos.sistema.adapter.inbound.exceptionhandler;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;


@ControllerAdvice
public class ExceptionControllerAdvice {

    private final MessageSource source;

    public ExceptionControllerAdvice(final MessageSource messageSource) {
        source = messageSource;
    }

    @ExceptionHandler(value = {ResponseStatusException.class})
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public ErrorMessage handleResponseStatusException(final ResponseStatusException ex, WebRequest request) {
        String path = getPath(request.getDescription(false));
        final String message = ex.getReason();
        return new ErrorMessage(ex.getStatus(), message, path);
    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        String path = getPath(request.getDescription(false));
        String message = ex.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();

        return new ErrorMessage(HttpStatus.BAD_REQUEST, message, path);
    }

    private String getPath(String path) {
        return path.replace("uri=", "");
    }
}

