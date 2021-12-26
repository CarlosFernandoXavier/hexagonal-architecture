package com.unisinos.sistema.application.builder;

import com.unisinos.sistema.application.domain.Filial;
import com.unisinos.sistema.application.domain.ItemEstoque;

import java.util.List;

public class SubsidiaryBuilder {
    private Filial subsidiary;

    public static SubsidiaryBuilder builder() {
        SubsidiaryBuilder subsidiaryBuilder = new SubsidiaryBuilder();
        subsidiaryBuilder.subsidiary = new Filial();
        return subsidiaryBuilder;
    }

    public SubsidiaryBuilder id(Integer id) {
        this.subsidiary.setId(id);
        return this;
    }

    public SubsidiaryBuilder nome(String nome) {
        this.subsidiary.setNome(nome);
        return this;
    }

    public SubsidiaryBuilder itens(List<ItemEstoque> itens) {
        this.subsidiary.setItens(itens);
        return this;
    }

    public Filial build() {
        return this.subsidiary;
    }
}
