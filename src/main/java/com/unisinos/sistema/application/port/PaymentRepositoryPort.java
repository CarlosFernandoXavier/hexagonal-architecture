package com.unisinos.sistema.application.port;

import com.unisinos.sistema.adapter.outbound.entity.PaymentEntity;

public interface PaymentRepositoryPort {
    PaymentEntity save(PaymentEntity paymentEntity);

    PaymentEntity getById(Integer id);

    void deleteById(Integer id);
}
