package org.example.bstest.demos.web.element.sort;

import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.entity.AgentEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopSort extends AbstractSort{

    @Override
    public void doSort(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO) {
        int expectNumber = recommendRequestDTO.getExpectNumber();
        List<AgentEntity> list2Sort = recommendResponseDTO.getAgentEntityList();
        if(list2Sort.size() < expectNumber) {
            return ;
        }
        List<AgentEntity>list = list2Sort
                .stream()
                .sorted((o1, o2) -> o2.getScore() - o1.getScore())
                .limit(expectNumber)
                .collect(Collectors.toList());
        recommendResponseDTO.setAgentEntityList(list);
    }



}
