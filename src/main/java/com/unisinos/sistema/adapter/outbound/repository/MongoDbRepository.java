package com.unisinos.sistema.adapter.outbound.repository;

import com.unisinos.sistema.adapter.outbound.entity.SubsidiaryEntity;
import com.unisinos.sistema.application.port.SubsidiaryRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MongoDbRepository implements SubsidiaryRepositoryPort {
    private FilialRepository filialRepository;

    @Override
    public List<SubsidiaryEntity> findAll() {
        return filialRepository.findAll();
    }

    @Override
    public SubsidiaryEntity getById(Integer id) {
        return filialRepository.getById(id);
    }

    @Override
    public SubsidiaryEntity save(SubsidiaryEntity subsidiaryEntity) {
        return filialRepository.save(subsidiaryEntity);
    }
}
