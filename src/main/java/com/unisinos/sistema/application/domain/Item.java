package com.unisinos.sistema.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Item {
    private String codigo;
    private String nome;
    private BigDecimal preco;
}
