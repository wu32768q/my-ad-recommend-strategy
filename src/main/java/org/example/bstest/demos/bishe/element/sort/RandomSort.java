package org.example.bstest.demos.bishe.element.sort;

import org.example.bstest.demos.bishe.DTO.RecommendRequestDTO;
import org.example.bstest.demos.bishe.DTO.RecommendResponseDTO;
import org.example.bstest.demos.bishe.entity.AgentEntity;
import org.example.bstest.demos.bishe.entity.elementEntity.ElementEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RandomSort extends AbstractSort{

    @Override
    public void process(RecommendRequestDTO recommendRequestDTO,
                        RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {
        doSort(recommendRequestDTO, recommendResponseDTO,  elementEntity);
    }

    @Override
    public void doSort(RecommendRequestDTO recommendRequestDTO,
                       RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {
        int expectNumber = recommendRequestDTO.getExpectNumber();
        List<AgentEntity> list2Sort = recommendResponseDTO.getAgentEntityList();
        if(list2Sort.size() < expectNumber) {
            return ;
        }
        Collections.shuffle(list2Sort);
        List<AgentEntity>list =  list2Sort.stream().limit(expectNumber).collect(Collectors.toList());
        recommendResponseDTO.setAgentEntityList(list);
    }



}
