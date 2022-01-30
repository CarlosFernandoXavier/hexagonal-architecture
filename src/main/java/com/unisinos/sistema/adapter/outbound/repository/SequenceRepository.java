package com.unisinos.sistema.adapter.outbound.repository;

import com.unisinos.sistema.adapter.outbound.entity.SequenceEntity;
import com.unisinos.sistema.application.port.SequenceRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Component
@AllArgsConstructor
public class SequenceRepository implements SequenceRepositoryPort {
    private MongoOperations mongoOperations;

    public Integer getSequence(String sequenceName) {
        //Get a database sequence
        Query query = new Query(Criteria.where("id").is(sequenceName));
        //Update the sequence
        Update update = new Update().inc("sequence", 1);
        //Change the db_sequence document
        SequenceEntity contador = mongoOperations
                .findAndModify(query,
                        update, options().returnNew(true).upsert(true),
                        SequenceEntity.class);

        return !Objects.isNull(contador) ? contador.getSequence() : 1;
    }
}
