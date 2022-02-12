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

        Query query = getDatabaseSequence(sequenceName);
        Update update = updateSequence();
        SequenceEntity counter = changeDBSequenceDocument(query, update);

        return !Objects.isNull(counter) ? counter.getSequence() : 1;
    }

    private Query getDatabaseSequence(String sequence) {
        return new Query(Criteria.where("id").is(sequence));
    }

    private Update updateSequence() {
        return new Update().inc("sequence", 1);
    }

    private SequenceEntity changeDBSequenceDocument(Query query, Update update) {
        return mongoOperations.findAndModify(query, update, options()
                .returnNew(true).upsert(true), SequenceEntity.class);
    }
}
