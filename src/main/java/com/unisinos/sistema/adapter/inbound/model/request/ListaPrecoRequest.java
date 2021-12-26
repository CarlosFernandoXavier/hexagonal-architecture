package com.unisinos.sistema.adapter.inbound.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ListaPrecoRequest {

    @ApiModelProperty(value = "Nome da lista da lista de preço", example = "Torra torra NH")
    @NotEmpty(message = "nome deve ser preenchido")
    private String nome;

    @ApiModelProperty(value = "Data inicial da lista de preço")
    @NotNull(message = "dataInicial deve ser preenchida")
    private LocalDateTime dataInicial;

    @ApiModelProperty(value = "Data de expiração da lista de preço")
    @NotNull(message = "dataFinal deve ser preenchida")
    private LocalDateTime dataFinal;

    @ApiModelProperty(value = "Itens da lista de preço")
    @NotNull(message = "Itens devem ser preenchidos")
    @Valid
    private List<ItemRequest> itens;

    @ApiModelProperty(value = "Filiais que será aplicado a lista de preço", example = "[701, 204]")
    @NotNull(message = "Filiais devem ser preenchidas")
    private List<Integer> filiais;
}
