package org.example.bstest.demos.web.element.sort;

import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.entity.AgentEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RandomSort extends AbstractSort{

    @Override
    public void process(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO) {
        doSort(recommendRequestDTO, recommendResponseDTO);
    }

    @Override
    public void doSort(RecommendRequestDTO recommendRequestDTO,
                                    RecommendResponseDTO recommendResponseDTO) {
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
