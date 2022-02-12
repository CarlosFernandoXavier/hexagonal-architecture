package com.unisinos.sistema.adapter.outbound.repository;

import com.unisinos.sistema.adapter.outbound.entity.SubsidiaryEntity;
import com.unisinos.sistema.application.port.SubsidiaryRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MongoDbSubsidiaryRepository implements SubsidiaryRepositoryPort {
    private SubsidiaryRepository subsidiaryRepository;

    @Override
    public List<SubsidiaryEntity> findAll() {
        return subsidiaryRepository.findAll();
    }

    @Override
    public SubsidiaryEntity getById(Integer id) {
        return subsidiaryRepository.getById(id);
    }

    @Override
    public SubsidiaryEntity save(SubsidiaryEntity subsidiaryEntity) {
        return subsidiaryRepository.save(subsidiaryEntity);
    }
}
