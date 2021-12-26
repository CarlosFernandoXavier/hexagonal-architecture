package com.unisinos.sistema.adapter.outbound.repository;

import com.unisinos.sistema.adapter.outbound.entity.PagamentoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends MongoRepository<PagamentoEntity, Integer> {
    PagamentoEntity save(PagamentoEntity pagamentoEntity);

    PagamentoEntity getById(Integer integer);
}
