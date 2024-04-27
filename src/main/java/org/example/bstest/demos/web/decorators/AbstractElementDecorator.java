package org.example.bstest.demos.web.decorators;

import lombok.Builder;
import lombok.Data;
import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.element.AbstractElement;



@Data
public abstract class AbstractElementDecorator {

    protected AbstractElement abstractElement;

    public abstract void process(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO);

    public AbstractElementDecorator(AbstractElement abstractElement) {
        this.abstractElement = abstractElement;
    }
}
