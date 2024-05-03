package org.example.bstest.demos.web.element.sort;

import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.entity.AgentEntity;
import org.example.bstest.demos.web.entity.elementEntity.ElementEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopSort extends AbstractSort{

    @Override
    public void doSort(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {
        int expectNumber = recommendRequestDTO.getExpectNumber();
        List<AgentEntity> list2Sort = recommendResponseDTO.getAgentEntityList();
        if(list2Sort.size() <= expectNumber) {
            return ;
        }
//        System.out.println(list2Sort);
        List<AgentEntity>list = list2Sort
                .stream()
                .sorted((o1, o2) -> o2.getQualityValue() - o1.getQualityValue())
                .limit(expectNumber)
                .collect(Collectors.toList());
//        System.out.println(list);
        recommendResponseDTO.setAgentEntityList(list);
    }



}
