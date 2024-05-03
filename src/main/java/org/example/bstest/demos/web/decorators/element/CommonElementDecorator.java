package org.example.bstest.demos.web.decorators.element;

import lombok.Builder;
import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.decorators.AbstractElementDecorator;
import org.example.bstest.demos.web.element.AbstractElement;
import org.example.bstest.demos.web.entity.elementEntity.ElementEntity;
import org.springframework.stereotype.Component;

@Component
@Builder
public class CommonElementDecorator extends AbstractElementDecorator {

    public CommonElementDecorator() {
        super();
    }

    public void process(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) throws InstantiationException, IllegalAccessException {

        abstractElement.process(recommendRequestDTO, recommendResponseDTO, elementEntity);
    }

    public CommonElementDecorator(AbstractElement abstractElement) {
        super();
    }

}
