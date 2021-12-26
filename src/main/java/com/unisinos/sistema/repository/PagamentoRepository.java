package com.unisinos.sistema.repository;

import com.unisinos.sistema.entity.PagamentoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoRepository extends MongoRepository<PagamentoEntity, Integer> {
    PagamentoEntity save(PagamentoEntity pagamentoEntity);

    PagamentoEntity getById(Integer integer);
}
