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
public class ItensListaPrecoRequest {
    @ApiModelProperty(value = "id da lista de preço", example = "8", required = true)
    @NotNull
    private Integer idPriceList;
    @ApiModelProperty(value = "itens da lista de preço")
    @NotNull
    private List<ItemRequest> itens;
}
