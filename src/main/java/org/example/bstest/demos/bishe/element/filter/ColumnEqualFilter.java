package org.example.bstest.demos.bishe.element.filter;

import org.example.bstest.demos.bishe.DTO.RecommendRequestDTO;
import org.example.bstest.demos.bishe.DTO.RecommendResponseDTO;
import org.example.bstest.demos.bishe.entity.elementEntity.ElementEntity;
import org.springframework.stereotype.Component;


@Component
public class ColumnEqualFilter extends  AbstractFilter {


    @Override
    public void doFilter(RecommendRequestDTO recommendRequestDTO,
                         RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {

    }

    @Override
    public void process(RecommendRequestDTO recommendRequestDTO,
                        RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {
        this.doFilter(recommendRequestDTO, recommendResponseDTO, elementEntity);
    }
}
