package org.example.bstest.demos.bishe.decorators;

import lombok.Data;
import org.example.bstest.demos.bishe.DTO.RecommendRequestDTO;
import org.example.bstest.demos.bishe.DTO.RecommendResponseDTO;
import org.example.bstest.demos.bishe.element.AbstractElement;
import org.example.bstest.demos.bishe.entity.elementEntity.ElementEntity;


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
