package com.unisinos.sistema.application.builder;

import com.unisinos.sistema.application.domain.Item;
import com.unisinos.sistema.application.domain.ListaPreco;

import java.time.LocalDateTime;
import java.util.List;

public class PriceListBuilder {
    private ListaPreco listaPreco;

    public static PriceListBuilder builder() {
        PriceListBuilder priceListBuilder = new PriceListBuilder();
        priceListBuilder.listaPreco = new ListaPreco();
        return priceListBuilder;
    }

    public PriceListBuilder id(Integer id) {
        listaPreco.setId(id);
        return this;
    }

    public PriceListBuilder nome(String nome) {
        listaPreco.setNome(nome);
        return this;
    }

    public PriceListBuilder dataInicial(LocalDateTime dataInicial) {
        listaPreco.setDataInicial(dataInicial);
        return this;
    }

    public PriceListBuilder dataFinal(LocalDateTime dataFinal) {
        listaPreco.setDataFinal(dataFinal);
        return this;
    }

    public PriceListBuilder itens(List<Item> itens) {
        listaPreco.setItens(itens);
        return this;
    }

    public PriceListBuilder filiais(List<Integer> filiais) {
        listaPreco.setFiliais(filiais);
        return this;
    }

    public ListaPreco build() {
        return listaPreco;
    }
}
