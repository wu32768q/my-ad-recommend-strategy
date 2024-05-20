package org.example.bstest.demos.bishe.element.recall;

import org.example.bstest.demos.bishe.DTO.RecommendRequestDTO;
import org.example.bstest.demos.bishe.DTO.RecommendResponseDTO;
import org.example.bstest.demos.bishe.element.AbstractElement;
import org.example.bstest.demos.bishe.entity.elementEntity.ElementEntity;
import org.springframework.stereotype.Component;

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
