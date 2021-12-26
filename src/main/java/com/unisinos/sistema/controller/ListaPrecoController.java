package com.unisinos.sistema.controller;

import com.unisinos.sistema.config.SwaggerConfig;
import com.unisinos.sistema.exceptionhandler.ErrorMessage;
import com.unisinos.sistema.model.request.ListaPrecoRequest;
import com.unisinos.sistema.model.response.ListaPrecoResponse;
import com.unisinos.sistema.service.ListaPrecoService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/v1/lista-preco")
@Api(tags = SwaggerConfig.LISTA_PRECO_V1)
@AllArgsConstructor
public class ListaPrecoController {

    ListaPrecoService listaPrecoService;

    @PostMapping("/adicionar")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Adicionar lista de preço")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED", response = ListaPrecoResponse.class),
            @ApiResponse(code = 412, message = "PRECONDITION_FAILED", response = ErrorMessage.class),
            @ApiResponse(code = 400, message = "BAD_REQUEST", response = ErrorMessage.class)
    })

    public ListaPrecoResponse addPriceList(@RequestBody @NotNull @Valid ListaPrecoRequest listaPrecoRequest) {
        return listaPrecoService.addPriceList(listaPrecoRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Exibir lista de preço")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ListaPrecoResponse.class),
            @ApiResponse(code = 404, message = "NOT_FOUND", response = ErrorMessage.class),
    })

    public List<ListaPrecoResponse> getPriceList(
            @ApiParam(name = "id", value = "Id da lista de preço", example = "4")
            @RequestParam(required = false) Integer id) {
        return listaPrecoService.getPriceList(id);
    }
}

