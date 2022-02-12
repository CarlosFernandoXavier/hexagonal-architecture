package com.unisinos.sistema.adapter.outbound.repository;

import com.unisinos.sistema.adapter.outbound.entity.ListaPrecoEntity;
import com.unisinos.sistema.application.port.PriceListRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MongoDbPriceListRepository implements PriceListRepositoryPort {

    private ListaPrecoRepository listaPrecoRepository;

    @Override
    public ListaPrecoEntity save(ListaPrecoEntity listaPrecoEntity) {
        return listaPrecoRepository.save(listaPrecoEntity);
    }

    @Override
    public List<ListaPrecoEntity> findAll() {
        return listaPrecoRepository.findAll();
    }

    @Override
    public ListaPrecoEntity getById(Integer id) {
        return listaPrecoRepository.getById(id);
    }
}
