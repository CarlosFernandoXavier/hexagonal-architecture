package com.unisinos.sistema.application.enumeration;

import java.util.EnumSet;

public enum FormaPagamentoEnum {
    DINHEIRO("1"),
    VALE_PRESENTE("2"),
    CARTAO_CREDITO("3"),
    CARTAO_DEBITO("4");

    private String codigo;

    FormaPagamentoEnum(String codigo) {
        this.codigo = codigo;
    }

    public static FormaPagamentoEnum getByCode(String codigo) {
        return EnumSet.allOf(FormaPagamentoEnum.class).stream()
                .filter(formaPagamentoEnum -> formaPagamentoEnum.codigo.equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
