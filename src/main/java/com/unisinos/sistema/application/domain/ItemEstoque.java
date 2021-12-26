package com.unisinos.sistema.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemEstoque {
    private String codigo;
    private String nome;
    private Integer quantidade;
    private String fornecedor;
}
