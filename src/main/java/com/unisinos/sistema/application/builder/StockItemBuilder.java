package com.unisinos.sistema.application.builder;

import com.unisinos.sistema.application.domain.ItemEstoque;

public class StockItemBuilder {
    private ItemEstoque itemEstoque;

    public static StockItemBuilder builder() {
        StockItemBuilder stockItemBuilder = new StockItemBuilder();
        stockItemBuilder.itemEstoque = new ItemEstoque();
        return stockItemBuilder;
    }

    public StockItemBuilder codigo(String codigo) {
        this.itemEstoque.setCodigo(codigo);
        return this;
    }

    public StockItemBuilder nome(String nome) {
        this.itemEstoque.setNome(nome);
        return this;
    }

    public StockItemBuilder quantidade(Integer quantidade) {
        this.itemEstoque.setQuantidade(quantidade);
        return this;
    }

    public StockItemBuilder fornecedor(String fornecedor) {
        this.itemEstoque.setFornecedor(fornecedor);
        return this;
    }

    public ItemEstoque build() {
        return this.itemEstoque;
    }
}
