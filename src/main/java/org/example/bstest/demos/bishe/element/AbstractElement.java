package org.example.bstest.demos.bishe.element;

import org.example.bstest.demos.bishe.DTO.RecommendRequestDTO;
import org.example.bstest.demos.bishe.DTO.RecommendResponseDTO;
import org.example.bstest.demos.bishe.entity.elementEntity.ElementEntity;

public interface AbstractElement {

    public  void process(RecommendRequestDTO recommendRequestDTO,
                         RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity);





}
