package org.example.bstest.demos.bishe.element.filter;

import org.example.bstest.demos.bishe.DTO.RecommendRequestDTO;
import org.example.bstest.demos.bishe.DTO.RecommendResponseDTO;
import org.example.bstest.demos.bishe.element.AbstractElement;
import org.example.bstest.demos.bishe.entity.elementEntity.ElementEntity;

public abstract class AbstractFilter implements AbstractElement {

    @Override
    public  void process(RecommendRequestDTO recommendRequestDTO,
                         RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {
        doFilter( recommendRequestDTO, recommendResponseDTO, elementEntity);
    }

    public abstract void doFilter(RecommendRequestDTO recommendRequestDTO,
                                  RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity);

}
