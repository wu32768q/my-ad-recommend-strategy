package org.example.bstest.demos.web.element.recall;

import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.element.AbstractElement;
import org.example.bstest.demos.web.entity.AgentEntity;

import java.util.List;

public abstract class AbstrackRecall implements AbstractElement {

    public abstract List<AgentEntity> doRecall(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO);

    @Override
    public void process(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO) {
        doRecall(recommendRequestDTO, recommendResponseDTO);
    }
}
