package com.unisinos.sistema.adapter.outbound.repository;

import com.unisinos.sistema.adapter.outbound.entity.PaymentEntity;
import com.unisinos.sistema.application.port.PaymentRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MongoDbPaymentRepository implements PaymentRepositoryPort {
    private final PaymentRepository paymentRepository;

    @Override
    public PaymentEntity save(PaymentEntity paymentEntity) {
        return paymentRepository.save(paymentEntity);
    }

    @Override
    public PaymentEntity getById(Integer id) {
        return paymentRepository.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        paymentRepository.deleteById(id);
    }
}
