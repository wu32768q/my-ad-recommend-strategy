package org.example.bstest;

import org.example.bstest.demos.web.entity.StrategyEntity;
import org.example.bstest.demos.web.mapper.StrategyMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BsTestApplicationTests {
    @Autowired
    StrategyMapper strategyMapper;



    @Test
    void contextLoads() {
        StrategyEntity strategyEntity = StrategyEntity.builder().description("heello").build();
        strategyMapper.insertStrategy(strategyEntity);


    }

}
