package com.unisinos.sistema.application.port;

import com.unisinos.sistema.adapter.outbound.entity.ListaPrecoEntity;

import java.util.List;

public interface PriceListRepositoryPort {
    ListaPrecoEntity save(ListaPrecoEntity listaPrecoEntity);

    List<ListaPrecoEntity> findAll();

    ListaPrecoEntity getById(Integer id);
}
