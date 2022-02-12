package com.unisinos.sistema.adapter.outbound.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document("pagamento")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentEntity {

    @Transient
    private static final String NOME_SEQUENCE = "pagamento_sequence";

    @Id
    private Integer id;
    private LocalDateTime data;
    private String formaPagamento;
    private List<ItemEntity> itens;
    private BigDecimal valorTotal;
}
