package org.example.bstest.demos.bishe.mapper.mongodb;


import org.bson.types.ObjectId;
import org.example.bstest.demos.bishe.entity.elementEntity.ElementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ElementMapper {

    @Autowired
    MongoTemplate mongoTemplate;
    static final String ELEMENT_COLLECTION = "element_collection";
    public void insertElement(ElementEntity elementEntity) {
        mongoTemplate.insert(elementEntity, ELEMENT_COLLECTION);
    }

    public void deleteElement(ObjectId id) {
        Query query = new Query(Criteria.where("element_id").is(id));
        mongoTemplate.remove(query, ELEMENT_COLLECTION);
    }


    public void updateElement(ElementEntity elementEntity) {
        Query query = new Query(Criteria.where("element_id").is(elementEntity.getElementId()));
        Update update = new Update();
        for (Map.Entry<String, Object> entry : elementEntity.getAllNotNullFieldMap().entrySet()) {
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();
            update.set(fieldName, fieldValue);
        }
        mongoTemplate.updateFirst(query, update, ELEMENT_COLLECTION);
    }


    public List<ElementEntity> getElementList(){
        return mongoTemplate.findAll(ElementEntity.class);
    };

    public ElementEntity getElementById(String id){
        return mongoTemplate.findById(id, ElementEntity.class);
    }

}
