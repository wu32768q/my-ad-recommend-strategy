package org.example.bstest.demos.bishe.element.filter;

import org.example.bstest.demos.bishe.DTO.RecommendRequestDTO;
import org.example.bstest.demos.bishe.DTO.RecommendResponseDTO;
import org.example.bstest.demos.bishe.entity.elementEntity.ElementEntity;

public class StatusFilter extends AbstractFilter{
    @Override
    public void doFilter(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {
        this.process(recommendRequestDTO, recommendResponseDTO, elementEntity);
    }

    @Override
    public void process(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {

    }
}
