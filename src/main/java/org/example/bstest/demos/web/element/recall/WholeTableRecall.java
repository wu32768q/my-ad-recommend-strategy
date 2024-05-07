package org.example.bstest.demos.web.element.recall;


import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.entity.AgentEntity;
import org.example.bstest.demos.web.entity.elementEntity.ElementEntity;
import org.example.bstest.demos.web.mapper.mysql.AgentMaterialMapper;
import org.example.bstest.demos.web.service.AgentService;
import org.example.bstest.demos.web.utils.MyStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component

public class WholeTableRecall extends AbstrackRecall{

    @Autowired
    AgentService agentService;

    @Autowired
    AgentMaterialMapper agentMaterialMapper;


    @Override
    public void doRecall(RecommendRequestDTO recommendRequestDTO,
                         RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {
        String tableName = recommendRequestDTO.getTableName2Recall();

//        System.out.println(agentService);
//        try {
//            agentService.checkatableName(tableName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String tableNameAfterHandle = MyStringUtils.concatenationWithQuotationMarks(tableName);
        if(Boolean.FALSE.equals(agentService.checkatableName(tableNameAfterHandle))) {
            return ;
        }
        List<AgentEntity> list = agentService.getAgentWholeTable(tableName);
//        list.stream().forEach(System.out::println);
        recommendResponseDTO.getAgentEntityList().addAll(list);

    }


    @Override
    public void process(RecommendRequestDTO recommendRequestDTO,
                        RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {
        doRecall(recommendRequestDTO, recommendResponseDTO, elementEntity);

    }
}
