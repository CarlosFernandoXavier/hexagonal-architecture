package com.unisinos.sistema.application.port;

import com.unisinos.sistema.adapter.outbound.entity.PagamentoEntity;

public interface PaymentRepositoryPort {
    PagamentoEntity save(PagamentoEntity pagamentoEntity);

    PagamentoEntity getById(Integer id);

    void deleteById(Integer id);
}
