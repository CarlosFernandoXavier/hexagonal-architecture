package com.unisinos.sistema.application.builder;

import com.unisinos.sistema.application.domain.Item;

import java.math.BigDecimal;

public class ItemBuilder {
    private Item item;

    public static ItemBuilder builder() {
        ItemBuilder itemBuilder = new ItemBuilder();
        itemBuilder.item = new Item();
        return itemBuilder;
    }

    public ItemBuilder codigo(String codigo) {
        this.item.setCodigo(codigo);
        return this;
    }

    public ItemBuilder nome(String nome) {
        this.item.setNome(nome);
        return this;
    }

    public ItemBuilder preco(BigDecimal preco) {
        this.item.setPreco(preco);
        return this;
    }

    public Item build() {
        return this.item;
    }
}
