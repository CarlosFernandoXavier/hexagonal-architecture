package com.unisinos.sistema.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FilialResponse {
    private Integer id;
    private String nome;
    private List<ItemEstoqueResponse> itens;
}
