package org.example.bstest.demos.web.element.recall;


import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.entity.AgentEntity;
import org.example.bstest.demos.web.service.AgentService;
import org.example.bstest.demos.web.utils.MyStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class WholeTableRecall extends AbstrackRecall{


    @Autowired
    AgentService agentService;

    @Override
    public List<AgentEntity> doRecall(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO) {
        String tableName = recommendRequestDTO.getTableName2Recall();
        if(Boolean.FALSE.equals(agentService.checkatableName(tableName))) {
            return Collections.emptyList();
        }
        String tableNameAfterHandle = MyStringUtils.concatenationWithQuotationMarks(tableName);
        return agentService.getAgentWholeTable(tableNameAfterHandle);

    }


    @Override
    public void process(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO) {
        doRecall(recommendRequestDTO, recommendResponseDTO);
    }
}
