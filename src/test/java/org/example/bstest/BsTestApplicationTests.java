package org.example.bstest;

import org.example.bstest.demos.web.entity.elementEntity.ElementEntity;
import org.example.bstest.demos.web.enums.ElementTypeEnum;
import org.example.bstest.demos.web.mapper.mongodb.StrategyMapper;
import org.example.bstest.demos.web.mapper.mysql.AgentMaterialMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import sun.management.Agent;

@SpringBootTest
class BsTestApplicationTests {
    @Autowired
    StrategyMapper strategyMapper;
    @Autowired
    MongoTemplate mongoTemplate;
    static final String STRATEGY_COLLECTION = "element_collection";

    @Autowired
    AgentMaterialMapper agentMaterialMapper;

    @Test
    void contextLoads() {
//        StrategyEntity strategyEntity = StrategyEntity.builder().description("heello").build();
//        strategyMapper.insertStrategy(strategyEntity);

//        ElementEntity elementEntity = ElementEntity.builder()
//                .elementTypeEnum(ElementTypeEnum.FILTER)
//                .elementName("testElement")
//                .build();
//        mongoTemplate.save(elementEntity,STRATEGY_COLLECTION);
        final String CHARACTER_HEAD_AND_TAIL = "'";
        String tableName = CHARACTER_HEAD_AND_TAIL+"ad_to_strategy_table"+CHARACTER_HEAD_AND_TAIL;

        System.out.println(agentMaterialMapper.tableNameCheck(tableName));
    }

}
