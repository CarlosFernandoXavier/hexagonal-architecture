package com.unisinos.sistema.controller;

import com.unisinos.sistema.config.SwaggerConfig;
import com.unisinos.sistema.entity.PagamentoEntity;
import com.unisinos.sistema.exceptionhandler.ErrorMessage;
import com.unisinos.sistema.model.request.PagamentoRequest;
import com.unisinos.sistema.model.request.PagamentoUpdateRequest;
import com.unisinos.sistema.model.response.ListaPrecoResponse;
import com.unisinos.sistema.model.response.PagamentoResponse;
import com.unisinos.sistema.service.PagamentoService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/v1/pagamento")
@Api(tags = SwaggerConfig.PAGAMENTO_V1)
@AllArgsConstructor
public class PagamentoController {

    private PagamentoService pagamentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Gravar pagamento")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED", response = PagamentoResponse.class),
            @ApiResponse(code = 412, message = "PRECONDITION_FAILED", response = ErrorMessage.class),
            @ApiResponse(code = 400, message = "BAD_REQUEST", response = ErrorMessage.class)
    })

    public PagamentoResponse payment(@RequestBody @NotNull @Valid PagamentoRequest pagamentoRequest) {
        return pagamentoService.payment(pagamentoRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Remover pagamento")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT_FOUND", response = ErrorMessage.class)
    })

    public void payment(@NotNull @ApiParam(name = "id", value = "Id do registro de pagamento")
                            @RequestParam Integer id) {
        pagamentoService.deletePayment(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Buscar pagamento")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ListaPrecoResponse.class),
            @ApiResponse(code = 404, message = "NOT_FOUND", response = ErrorMessage.class),
    })

    public PagamentoEntity findPaymentById(
            @ApiParam(name = "id", value = "Id do registro de pagamento")
            @RequestParam Integer id) {
        return pagamentoService.findPaymentById(id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Atualizar pagamento")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = PagamentoResponse.class),
            @ApiResponse(code = 412, message = "PRECONDITION_FAILED", response = ErrorMessage.class),
            @ApiResponse(code = 400, message = "BAD_REQUEST", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "NOT_FOUND", response = ErrorMessage.class)
    })

    public PagamentoResponse updatePayment(@RequestBody @NotNull @Valid PagamentoUpdateRequest pagamento) {
        return pagamentoService.updatePayment(pagamento);
    }
}
