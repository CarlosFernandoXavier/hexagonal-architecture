package com.unisinos.sistema.adapter.inbound.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RemoveItemRequest {
    @ApiModelProperty(name = "ids dos itens a serem removidos", example = "[1,2]", required = true)
    @NotNull
    private List<String> idItens;
    @ApiModelProperty(name = "id da lista de preço", example = "1", required = true)
    @NotNull
    private Integer idListaPreco;
}
