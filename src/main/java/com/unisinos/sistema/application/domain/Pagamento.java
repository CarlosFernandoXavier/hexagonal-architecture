package com.unisinos.sistema.application.domain;

import com.unisinos.sistema.application.enumeration.FormaPagamentoEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Pagamento {
    private Integer id;
    private FormaPagamentoEnum formaPagamento;
    private List<Item> itens;
    private BigDecimal valorTotal;
    private LocalDateTime data;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FormaPagamentoEnum getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamentoEnum formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
