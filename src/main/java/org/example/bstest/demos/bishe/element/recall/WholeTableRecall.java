package org.example.bstest.demos.bishe.element.recall;


import org.example.bstest.demos.bishe.DTO.RecommendRequestDTO;
import org.example.bstest.demos.bishe.DTO.RecommendResponseDTO;
import org.example.bstest.demos.bishe.entity.AgentEntity;
import org.example.bstest.demos.bishe.entity.elementEntity.ElementEntity;
import org.example.bstest.demos.bishe.mapper.mysql.AgentMaterialMapper;
import org.example.bstest.demos.bishe.service.AgentService;
import org.example.bstest.demos.bishe.utils.MyStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
