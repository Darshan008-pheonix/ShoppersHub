package com.sph.book.dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.sph.book.config.MongoConfig;
import com.sph.book.entity.SagaDatabaseSequence;




@Service
public class SagaCounterService {
	
	@Autowired
	MongoConfig mongoConfig;

   

    public long getNextSequence(String sequenceName) {
    	MongoTemplate dbCon = mongoConfig.getConnection("SagaCounter");

        Query query = new Query(Criteria.where("_id").is(sequenceName));

        Update update = new Update().inc("seq", 1);

        FindAndModifyOptions options = FindAndModifyOptions.options()
                .returnNew(true)
                .upsert(true);

        SagaDatabaseSequence counter = dbCon.findAndModify(
                query,
                update,
                options,
                SagaDatabaseSequence.class
        );

        return counter.getSeq();
    }
}