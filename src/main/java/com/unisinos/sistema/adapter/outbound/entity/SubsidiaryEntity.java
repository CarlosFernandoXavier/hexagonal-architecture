package com.unisinos.sistema.adapter.outbound.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("filial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SubsidiaryEntity {
    @Transient
    private static final String NOME_SEQUENCE = "filial_sequence";

    @Id
    private Integer id;
    private String nome;
    private List<ItemEstoqueEntity> itens;
}
