package com.unisinos.sistema.adapter.outbound.repository;

import com.unisinos.sistema.adapter.outbound.entity.PriceListEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceListRepository extends MongoRepository<PriceListEntity, Integer> {

    PriceListEntity save(PriceListEntity priceListEntity);

    PriceListEntity getById(Integer codigo);

    List<PriceListEntity> findAll();
}

