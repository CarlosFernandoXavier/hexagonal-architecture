package com.unisinos.sistema.adapter.inbound.controller;

import com.unisinos.sistema.adapter.inbound.configuration.SwaggerConfiguration;
import com.unisinos.sistema.adapter.inbound.exceptionhandler.ErrorMessage;
import com.unisinos.sistema.adapter.inbound.model.request.ListaPrecoRequest;
import com.unisinos.sistema.application.domain.ListaPreco;
import com.unisinos.sistema.application.service.ListaPrecoServiceImpl;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/v1/lista-preco")
@Api(tags = SwaggerConfiguration.LISTA_PRECO_V1)
@AllArgsConstructor
public class ListaPrecoController {

    ListaPrecoServiceImpl listaPrecoServiceImpl;

    @PostMapping("/adicionar")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Adicionar lista de preço")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED", response = ListaPreco.class),
            @ApiResponse(code = 412, message = "PRECONDITION_FAILED", response = ErrorMessage.class),
            @ApiResponse(code = 400, message = "BAD_REQUEST", response = ErrorMessage.class)
    })

    public ListaPreco addPriceList(@RequestBody @NotNull @Valid ListaPrecoRequest listaPrecoRequest) {
        return listaPrecoServiceImpl.addPriceList(listaPrecoRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Exibir lista de preço")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ListaPreco.class),
            @ApiResponse(code = 404, message = "NOT_FOUND", response = ErrorMessage.class),
    })

    public List<ListaPreco> getPriceList(
            @ApiParam(name = "id", value = "Id da lista de preço", example = "4")
            @RequestParam(required = false) Integer id) {
        return listaPrecoServiceImpl.getPriceList(id);
    }
}

