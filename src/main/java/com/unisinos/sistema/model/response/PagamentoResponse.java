package com.unisinos.sistema.model.response;

import com.unisinos.sistema.enumeration.FormaPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PagamentoResponse {
    private Integer id;
    private FormaPagamentoEnum formaPagamento;
    private List<ItemResponse> itens;
    private BigDecimal valorTotal;
    private LocalDateTime data;
}
