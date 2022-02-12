package com.unisinos.sistema.adapter.outbound.repository;

import com.unisinos.sistema.adapter.outbound.entity.PriceListEntity;
import com.unisinos.sistema.application.port.PriceListRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MongoDbPriceListRepository implements PriceListRepositoryPort {

    private PriceListRepository priceListRepository;

    @Override
    public PriceListEntity save(PriceListEntity priceListEntity) {
        return priceListRepository.save(priceListEntity);
    }

    @Override
    public List<PriceListEntity> findAll() {
        return priceListRepository.findAll();
    }

    @Override
    public PriceListEntity getById(Integer id) {
        return priceListRepository.getById(id);
    }
}
