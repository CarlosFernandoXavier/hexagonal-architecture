package com.unisinos.sistema.adapter.inbound.exceptionhandler;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorMessage {

    private Integer status;
    private String message;
    private String erro;
    private String path;

    public ErrorMessage(HttpStatus httpStatus, String message, String path) {
        this.status = httpStatus.value();
        this.erro = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = path;
    }
}
