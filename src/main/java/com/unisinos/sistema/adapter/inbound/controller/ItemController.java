package com.unisinos.sistema.adapter.inbound.controller;

import com.unisinos.sistema.adapter.configuration.SwaggerConfiguration;
import com.unisinos.sistema.adapter.inbound.exceptionhandler.ErrorMessage;
import com.unisinos.sistema.adapter.inbound.model.request.ItensListaPrecoRequest;
import com.unisinos.sistema.adapter.inbound.model.request.RemoveItemRequest;
import com.unisinos.sistema.application.domain.ListaPreco;
import com.unisinos.sistema.application.port.PriceListServicePort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/item")
@Api(tags = SwaggerConfiguration.ITEM_V1)
@AllArgsConstructor
public class ItemController {

    private PriceListServicePort priceListServicePort;

    @PostMapping("/adicionar")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Adicionar itens à lista de preço")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ListaPreco.class),
            @ApiResponse(code = 400, message = "BAD_REQUEST", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "NOT_FOUND", response = ErrorMessage.class),
            @ApiResponse(code = 412, message = "PRECONDITION_FAILED", response = ErrorMessage.class)
    })

    public ListaPreco addItem(@RequestBody @NotNull @Valid ItensListaPrecoRequest itensListaPrecoRequest) {
        return priceListServicePort.addItem(itensListaPrecoRequest);
    }

    @DeleteMapping("/remover")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Remover itens da lista de preço")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ListaPreco.class),
            @ApiResponse(code = 400, message = "BAD_REQUEST", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "NOT_FOUND", response = ErrorMessage.class)
    })

    public ListaPreco removeListItem(@NotNull @Valid @RequestBody RemoveItemRequest removeItemRequest) {
        return priceListServicePort.removeItem(removeItemRequest);
    }
}
