package com.unisinos.sistema.adapter.inbound.model.request;

import com.unisinos.sistema.application.enumeration.FormaPagamentoEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PagamentoRequest {
    @ApiModelProperty(value = "Forma de pagamento", required = true)
    @NotNull(message = "formaPagamento deve ser preenchida")
    private FormaPagamentoEnum formaPagamento;
    @ApiModelProperty(value = "Itens", required = true)
    @Valid
    @NotNull(message = "Lista de itens deve ser preenchida")
    private List<ItemRequest> itens;
    @ApiModelProperty(value = "Valor total da compra", required = true)
    @NotNull(message = "ValorTotal da compra deve ser preenchido")
    private BigDecimal valorTotal;
    @ApiModelProperty(value = "Valor do vale presente")
    private BigDecimal valorCupom;
}
