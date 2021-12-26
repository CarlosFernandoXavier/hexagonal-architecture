package com.unisinos.sistema.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Filial {
    private Integer id;
    private String nome;
    private List<ItemEstoque> itens;
}
