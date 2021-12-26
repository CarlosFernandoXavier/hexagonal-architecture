package com.unisinos.sistema.application.domain;

import com.unisinos.sistema.application.enumeration.FormaPagamentoEnum;
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
public class Pagamento {
    private Integer id;
    private FormaPagamentoEnum formaPagamento;
    private List<Item> itens;
    private BigDecimal valorTotal;
    private LocalDateTime data;
}
