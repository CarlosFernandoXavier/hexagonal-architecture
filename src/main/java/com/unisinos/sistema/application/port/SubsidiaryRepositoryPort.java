package com.unisinos.sistema.application.port;

import com.unisinos.sistema.adapter.outbound.entity.SubsidiaryEntity;

import java.util.List;

public interface SubsidiaryRepositoryPort {
    List<SubsidiaryEntity> findAll();

    SubsidiaryEntity getById(Integer id);

    SubsidiaryEntity save(SubsidiaryEntity subsidiaryEntity);
}
