package org.example.bstest.demos.web.decorators;

import lombok.Data;
import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.element.AbstractElement;
import org.example.bstest.demos.web.entity.elementEntity.ElementEntity;


@Data
public abstract class AbstractElementDecorator {

    protected AbstractElement abstractElement;

    public void AbstractElement() {

    }

    public abstract void process(RecommendRequestDTO recommendRequestDTO,
                                 RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity)
            throws InstantiationException, IllegalAccessException;

    public AbstractElementDecorator() {
        this.abstractElement = abstractElement;
    }
}
