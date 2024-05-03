package org.example.bstest.demos.web.element.filter;

import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.element.AbstractElement;
import org.example.bstest.demos.web.entity.elementEntity.ElementEntity;

public abstract class AbstractFilter implements AbstractElement {

    @Override
    public  void process(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {
        doFilter( recommendRequestDTO, recommendResponseDTO, elementEntity);
    }

    public abstract void doFilter(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity);

}
