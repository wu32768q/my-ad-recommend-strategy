package org.example.bstest.demos.web.element.filter;

import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.entity.elementEntity.ElementEntity;

public class ColumnEqualFilter extends  AbstractFilter {


    @Override
    public void doFilter(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {

    }

    @Override
    public void process(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {
        this.doFilter(recommendRequestDTO, recommendResponseDTO, elementEntity);
    }
}
