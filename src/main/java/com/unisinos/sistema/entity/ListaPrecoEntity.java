package com.unisinos.sistema.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("listaDePreco")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ListaPrecoEntity {

    @Transient
    private static final String NOME_SEQUENCE = "lista_preco_sequence";

    @Id
    private Integer id;
    private String nome;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
    private List<ItemEntity> itens;
    private List<Integer> filiais;


}
