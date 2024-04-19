package org.example.bstest.demos.web.mapper;

import org.bson.types.ObjectId;
import org.example.bstest.demos.web.entity.StrategyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class StrategyMapper {

    @Autowired
    MongoTemplate mongoTemplate;


    static final String STRATEGY_COLLECTION = "strategy_collection";

    public void insertStrategy(StrategyEntity strategyEntity) {
        mongoTemplate.insert(strategyEntity, STRATEGY_COLLECTION);
    }

    public void deleteStrategy(ObjectId id) {
        Query query = new Query(Criteria.where("strategyId").is(id));
        mongoTemplate.remove(query, STRATEGY_COLLECTION);
    }


    public void updateStrategy(StrategyEntity strategyEntity) {
        Query query = new Query(Criteria.where("strategyId").is(strategyEntity.getStrategyId()));
        Update update = new Update();
        for (Map.Entry<String, Object> entry : strategyEntity.getAllNotNullFieldMap().entrySet()) {
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();
            update.set(fieldName, fieldValue);
        }
        mongoTemplate.updateFirst(query, update, STRATEGY_COLLECTION);
    }



    public StrategyEntity getStrategy(ObjectId id) {
        return mongoTemplate.findById(id, StrategyEntity.class, STRATEGY_COLLECTION);
    }

    public List<StrategyEntity> getStrategyList(){
        return mongoTemplate.findAll(StrategyEntity.class);
    }


}
