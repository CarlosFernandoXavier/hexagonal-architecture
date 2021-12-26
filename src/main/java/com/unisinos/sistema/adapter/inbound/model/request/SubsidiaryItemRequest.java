package com.unisinos.sistema.adapter.inbound.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubsidiaryItemRequest {
    @NotNull(message = "id da filial deve ser preenchido")
    private Integer subsidiaryId;
    @Valid
    @NotNull(message = "o campo itens não pode ser nulo")
    private List<ItemEstoqueRequest> itens;
}
