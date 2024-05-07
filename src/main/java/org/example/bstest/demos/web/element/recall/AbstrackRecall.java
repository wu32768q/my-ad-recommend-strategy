package org.example.bstest.demos.web.element.recall;

import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.element.AbstractElement;
import org.example.bstest.demos.web.entity.AgentEntity;
import org.example.bstest.demos.web.entity.elementEntity.ElementEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public abstract class AbstrackRecall implements AbstractElement {

    public abstract void doRecall(RecommendRequestDTO recommendRequestDTO,
                                  RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity);

    @Override
    public void process(RecommendRequestDTO recommendRequestDTO,
                        RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {
        doRecall(recommendRequestDTO, recommendResponseDTO, elementEntity);
    }
}
