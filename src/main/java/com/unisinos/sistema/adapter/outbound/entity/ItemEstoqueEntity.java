package com.unisinos.sistema.adapter.outbound.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemEstoqueEntity {
    private String codigo;
    private String nome;
    private Integer quantidade;
    private String fornecedor;
}
