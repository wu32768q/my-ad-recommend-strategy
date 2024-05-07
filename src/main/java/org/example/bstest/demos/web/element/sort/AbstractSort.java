package org.example.bstest.demos.web.element.sort;

import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.element.AbstractElement;
import org.example.bstest.demos.web.entity.AgentEntity;
import org.example.bstest.demos.web.entity.elementEntity.ElementEntity;

import java.util.List;

public abstract class AbstractSort implements AbstractElement {



    @Override
    public void process(RecommendRequestDTO recommendRequestDTO,
                        RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {
        doSort( recommendRequestDTO, recommendResponseDTO, elementEntity);
    }


    public abstract void doSort(RecommendRequestDTO recommendRequestDTO,
                                RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity);




}
