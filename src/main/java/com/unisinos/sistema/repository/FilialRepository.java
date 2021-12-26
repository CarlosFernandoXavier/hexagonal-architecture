package com.unisinos.sistema.repository;

import com.unisinos.sistema.entity.FilialEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilialRepository extends MongoRepository<FilialEntity, Integer> {
    FilialEntity getById(Integer id);
}
