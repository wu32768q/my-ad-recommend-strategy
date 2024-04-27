package org.example.bstest.demos.web.decorators.element;

import lombok.Builder;
import lombok.Data;
import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.decorators.AbstractElementDecorator;
import org.example.bstest.demos.web.element.AbstractElement;


public class CommonElementDecorator extends AbstractElementDecorator {



    public void process(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO)  {
        abstractElement.process(recommendRequestDTO, recommendResponseDTO);
    }

    public CommonElementDecorator(AbstractElement abstractElement) {
        super(abstractElement);
    }

}
