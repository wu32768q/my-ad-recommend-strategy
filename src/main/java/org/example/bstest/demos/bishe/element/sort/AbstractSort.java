package org.example.bstest.demos.bishe.element.sort;

import org.example.bstest.demos.bishe.DTO.RecommendRequestDTO;
import org.example.bstest.demos.bishe.DTO.RecommendResponseDTO;
import org.example.bstest.demos.bishe.element.AbstractElement;
import org.example.bstest.demos.bishe.entity.elementEntity.ElementEntity;

public abstract class AbstractSort implements AbstractElement {



    @Override
    public void process(RecommendRequestDTO recommendRequestDTO,
                        RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {
        doSort( recommendRequestDTO, recommendResponseDTO, elementEntity);
    }


    public abstract void doSort(RecommendRequestDTO recommendRequestDTO,
                                RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity);




}
