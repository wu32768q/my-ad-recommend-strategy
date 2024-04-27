package org.example.bstest.demos.web.element.sort;

import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.element.AbstractElement;
import org.example.bstest.demos.web.entity.AgentEntity;

import java.util.List;

public abstract class AbstractSort implements AbstractElement {



    @Override
    public void process(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO) {
        doSort( recommendRequestDTO, recommendResponseDTO);
    }


    public abstract void doSort(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO);




}
