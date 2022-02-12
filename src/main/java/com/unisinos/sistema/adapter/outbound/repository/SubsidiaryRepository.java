package com.unisinos.sistema.adapter.outbound.repository;

import com.unisinos.sistema.adapter.outbound.entity.SubsidiaryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubsidiaryRepository extends MongoRepository<SubsidiaryEntity, Integer> {
    SubsidiaryEntity getById(Integer id);
}
