package com.unisinos.sistema.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemEstoqueResponse {
    private String codigo;
    private String nome;
    private Integer quantidade;
    private String fornecedor;
}
