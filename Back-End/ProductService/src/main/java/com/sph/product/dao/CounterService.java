package com.sph.product.dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.sph.product.entity.DatabaseSequence;

@Service
public class CounterService {

    private final MongoTemplate mongoTemplate;

    public CounterService( @Qualifier("counterMongoTemplate")MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public long getNextSequence(String sequenceName) {

        Query query = new Query(Criteria.where("_id").is(sequenceName));

        Update update = new Update().inc("seq", 1);

        FindAndModifyOptions options = FindAndModifyOptions.options()
                .returnNew(true)
                .upsert(true);

        DatabaseSequence counter = mongoTemplate.findAndModify(
                query,
                update,
                options,
                DatabaseSequence.class
        );

        return counter.getSeq();
    }
}