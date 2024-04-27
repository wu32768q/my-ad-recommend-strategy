package org.example.bstest.demos.web.element.filter;

import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.element.AbstractElement;

public abstract class AbstractFilter implements AbstractElement {

    @Override
    public  void process(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO) {
        doFilter( recommendRequestDTO, recommendResponseDTO);
    }

    public abstract void doFilter(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO);

}
