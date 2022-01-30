package com.unisinos.sistema.adapter.inbound.controller;

import com.unisinos.sistema.adapter.configuration.SwaggerConfiguration;
import com.unisinos.sistema.adapter.inbound.exceptionhandler.ErrorMessage;
import com.unisinos.sistema.adapter.inbound.model.request.FilialRequest;
import com.unisinos.sistema.adapter.inbound.model.request.SubsidiaryItemRequest;
import com.unisinos.sistema.application.domain.Filial;
import com.unisinos.sistema.application.port.SubsidiaryServicePort;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/v1/filial")
@Api(tags = SwaggerConfiguration.FILIAL_V1)
@AllArgsConstructor
public class FilialController {

    private SubsidiaryServicePort subsidiaryServicePort;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Buscar filiais")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = Filial.class),
            @ApiResponse(code = 404, message = "NOT_FOUND", response = ErrorMessage.class),
    })

    public List<Filial> getSubsidiary(
            @ApiParam(name = "id", value = "Id filial", example = "4")
            @RequestParam(required = false) Integer id) {
        return subsidiaryServicePort.getSubsidiary(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Gravar filial")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED", response = Filial.class)
    })

    public Filial createSubsidiary(@RequestBody @NotNull @Valid FilialRequest filialRequest) {
        return subsidiaryServicePort.createSubsidiary(filialRequest);
    }

    @PostMapping("item")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Adicionar itens à filial")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = Filial.class)
    })
    public Filial addItens(@RequestBody @NotNull SubsidiaryItemRequest subsidiaryItemRequest) {
        return subsidiaryServicePort.addItem(subsidiaryItemRequest);
    }
}
