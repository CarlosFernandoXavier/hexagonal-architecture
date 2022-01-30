package com.unisinos.sistema.adapter.inbound.controller;

import com.unisinos.sistema.adapter.configuration.SwaggerConfiguration;
import com.unisinos.sistema.adapter.inbound.exceptionhandler.ErrorMessage;
import com.unisinos.sistema.adapter.inbound.model.request.PagamentoRequest;
import com.unisinos.sistema.adapter.inbound.model.request.PagamentoUpdateRequest;
import com.unisinos.sistema.application.domain.ListaPreco;
import com.unisinos.sistema.application.domain.Pagamento;
import com.unisinos.sistema.adapter.outbound.entity.PagamentoEntity;
import com.unisinos.sistema.application.port.PaymentServicePort;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/pagamento")
@Api(tags = SwaggerConfiguration.PAGAMENTO_V1)
@AllArgsConstructor
public class PagamentoController {

    private PaymentServicePort paymentServicePort;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Gravar pagamento")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED", response = Pagamento.class),
            @ApiResponse(code = 412, message = "PRECONDITION_FAILED", response = ErrorMessage.class),
            @ApiResponse(code = 400, message = "BAD_REQUEST", response = ErrorMessage.class)
    })

    public Pagamento payment(@RequestBody @NotNull @Valid PagamentoRequest pagamentoRequest) {
        return paymentServicePort.payment(pagamentoRequest);
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
        paymentServicePort.deletePayment(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Buscar pagamento")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ListaPreco.class),
            @ApiResponse(code = 404, message = "NOT_FOUND", response = ErrorMessage.class),
    })

    public PagamentoEntity findPaymentById(
            @ApiParam(name = "id", value = "Id do registro de pagamento")
            @RequestParam Integer id) {
        return paymentServicePort.findPaymentById(id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Atualizar pagamento")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = Pagamento.class),
            @ApiResponse(code = 412, message = "PRECONDITION_FAILED", response = ErrorMessage.class),
            @ApiResponse(code = 400, message = "BAD_REQUEST", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "NOT_FOUND", response = ErrorMessage.class)
    })

    public Pagamento updatePayment(@RequestBody @NotNull @Valid PagamentoUpdateRequest pagamento) {
        return paymentServicePort.updatePayment(pagamento);
    }
}
