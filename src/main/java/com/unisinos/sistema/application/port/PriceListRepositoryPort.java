package com.unisinos.sistema.application.port;

import com.unisinos.sistema.adapter.outbound.entity.PriceListEntity;

import java.util.List;

public interface PriceListRepositoryPort {
    PriceListEntity save(PriceListEntity priceListEntity);

    List<PriceListEntity> findAll();

    PriceListEntity getById(Integer id);
}
