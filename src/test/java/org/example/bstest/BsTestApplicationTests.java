package org.example.bstest;

import org.example.bstest.demos.bishe.constants.KafkaConstants;
import org.example.bstest.demos.bishe.mapper.mongodb.StrategyMapper;
import org.example.bstest.demos.bishe.mapper.mysql.AgentMaterialMapper;
import org.example.bstest.demos.bishe.proxy.KafkaProducerProxy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;

@SpringBootTest
class BsTestApplicationTests {
    @Autowired
    StrategyMapper strategyMapper;
    @Autowired
    MongoTemplate mongoTemplate;
    static final String STRATEGY_COLLECTION = "element_collection";

    @Autowired
    AgentMaterialMapper agentMaterialMapper;

    @Resource
    KafkaProducerProxy kafkaProducerProxy;


    @Test
    void contextLoads() {
//        StrategyEntity strategyEntity = StrategyEntity.builder().description("heello").build();
//        strategyMapper.insertStrategy(strategyEntity);

//        ElementEntity elementEntity = ElementEntity.builder()
//                .elementTypeEnum(ElementTypeEnum.FILTER)
//                .elementName("testElement")
//                .build();
//        mongoTemplate.save(elementEntity,STRATEGY_COLLECTION);
//        final String CHARACTER_HEAD_AND_TAIL = "'";
//        String tableName = CHARACTER_HEAD_AND_TAIL+"ad_to_strategy_table"+CHARACTER_HEAD_AND_TAIL;

//        System.out.println(agentMaterialMapper.tableNameCheck(tableName));
        for(int i = 0 ;i<1000; i++) {
            kafkaProducerProxy.send(KafkaConstants.RECOMMEND_COUNT_INC_TOPIC, i + "@@@@@");
        }



    }

}
