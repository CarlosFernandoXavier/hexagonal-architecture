package com.unisinos.sistema.application.domain;

import java.time.LocalDateTime;
import java.util.List;

public class ListaPreco {
    private Integer id;
    private String nome;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
    private List<Item> itens;
    private List<Integer> filiais;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDateTime dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDateTime getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDateTime dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public List<Integer> getFiliais() {
        return filiais;
    }

    public void setFiliais(List<Integer> filiais) {
        this.filiais = filiais;
    }
}
