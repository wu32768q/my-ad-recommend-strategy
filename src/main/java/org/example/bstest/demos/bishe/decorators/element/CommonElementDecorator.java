package org.example.bstest.demos.bishe.decorators.element;

import lombok.Builder;
import org.example.bstest.demos.bishe.DTO.RecommendRequestDTO;
import org.example.bstest.demos.bishe.DTO.RecommendResponseDTO;
import org.example.bstest.demos.bishe.decorators.AbstractElementDecorator;
import org.example.bstest.demos.bishe.element.AbstractElement;
import org.example.bstest.demos.bishe.entity.elementEntity.ElementEntity;
import org.springframework.stereotype.Component;

@Component
@Builder
public class CommonElementDecorator extends AbstractElementDecorator {

    public CommonElementDecorator() {
        super();
    }

    @Override
    public void process(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO,
                        ElementEntity elementEntity) throws InstantiationException, IllegalAccessException {

        abstractElement.process(recommendRequestDTO, recommendResponseDTO, elementEntity);
    }

    public CommonElementDecorator(AbstractElement abstractElement) {
        super();
    }

}
