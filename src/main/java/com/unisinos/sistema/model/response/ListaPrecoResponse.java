package com.unisinos.sistema.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ListaPrecoResponse {
    private Integer id;
    private String nome;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
    private List<ItemResponse> itens;
    private List<Integer> filiais;
}
