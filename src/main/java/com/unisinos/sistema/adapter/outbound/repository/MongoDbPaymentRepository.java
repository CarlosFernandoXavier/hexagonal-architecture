package com.unisinos.sistema.adapter.outbound.repository;

import com.unisinos.sistema.adapter.outbound.entity.PagamentoEntity;
import com.unisinos.sistema.application.port.PaymentRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MongoDbPaymentRepository implements PaymentRepositoryPort {
    private final PagamentoRepository pagamentoRepository;

    @Override
    public PagamentoEntity save(PagamentoEntity pagamentoEntity) {
        return pagamentoRepository.save(pagamentoEntity);
    }

    @Override
    public PagamentoEntity getById(Integer id) {
        return pagamentoRepository.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        pagamentoRepository.deleteById(id);
    }
}
