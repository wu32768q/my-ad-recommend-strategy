package org.example.bstest.demos.web.decorators.element;

import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.decorators.AbstractElementDecorator;
import org.example.bstest.demos.web.entity.elementEntity.ElementEntity;

public class NoTraceLogElementDecorator extends AbstractElementDecorator {
    @Override
    public void process(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) throws InstantiationException, IllegalAccessException {

    }
}
