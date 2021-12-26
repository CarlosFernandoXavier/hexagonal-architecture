package com.unisinos.sistema.application.builder;

import com.unisinos.sistema.application.domain.Item;
import com.unisinos.sistema.application.domain.Pagamento;
import com.unisinos.sistema.application.enumeration.FormaPagamentoEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PaymentBuilder {
    private Pagamento pagamento;

    public static PaymentBuilder builder() {
        PaymentBuilder paymentBuilder = new PaymentBuilder();
        paymentBuilder.pagamento = new Pagamento();
        return paymentBuilder;
    }

    public PaymentBuilder id(Integer id) {
        pagamento.setId(id);
        return this;
    }

    public PaymentBuilder formaPagamento(FormaPagamentoEnum formaPagamento) {
        pagamento.setFormaPagamento(formaPagamento);
        return this;
    }

    public PaymentBuilder itens(List<Item> itens) {
        pagamento.setItens(itens);
        return this;
    }

    public PaymentBuilder valorTotal(BigDecimal valorTotal) {
        pagamento.setValorTotal(valorTotal);
        return this;
    }

    public PaymentBuilder data(LocalDateTime data) {
        pagamento.setData(data);
        return this;
    }

    public Pagamento build() {
        return pagamento;
    }
}
