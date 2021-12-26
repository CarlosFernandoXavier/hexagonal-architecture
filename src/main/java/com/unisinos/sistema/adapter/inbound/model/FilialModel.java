package com.unisinos.sistema.adapter.inbound.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FilialModel {
    private Integer id;
    private String nome;
    private List<ItemEstoqueModel> itens;
}
