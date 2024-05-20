package org.example.bstest.demos.bishe.decorators.element;

import org.example.bstest.demos.bishe.DTO.RecommendRequestDTO;
import org.example.bstest.demos.bishe.DTO.RecommendResponseDTO;
import org.example.bstest.demos.bishe.decorators.AbstractElementDecorator;
import org.example.bstest.demos.bishe.entity.elementEntity.ElementEntity;

public class NoTraceLogElementDecorator extends AbstractElementDecorator {
    @Override
    public void process(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO,
                        ElementEntity elementEntity) throws InstantiationException, IllegalAccessException {

    }
}
