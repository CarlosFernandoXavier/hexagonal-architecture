package com.unisinos.sistema.adapter.outbound.repository;

import com.unisinos.sistema.adapter.outbound.entity.ListaPrecoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaPrecoRepository extends MongoRepository<ListaPrecoEntity, Integer> {

    ListaPrecoEntity save(ListaPrecoEntity listaPrecoEntity);

    ListaPrecoEntity getById(Integer codigo);

    List<ListaPrecoEntity> findAll();
}

