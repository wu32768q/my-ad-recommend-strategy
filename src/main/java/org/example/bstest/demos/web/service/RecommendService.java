package org.example.bstest.demos.web.service;

import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.DTO.RouteResponseDTO;

public interface RecommendService {

    public RecommendResponseDTO doRecommend(RecommendRequestDTO recommendRequestDTO);

    public void fillStrategyIdByAdId(RecommendRequestDTO recommendRequestDTO);

    public  RecommendResponseDTO buildInitRecommeendResponse();

    public Boolean isResEnough(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO);

    public void backstopHandle(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO);

    public Boolean requestCheck(RecommendRequestDTO recommendRequestDTO);

    public void buildFinalResponse(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, Boolean flag, String msg);

    public void fillRequeset(RecommendRequestDTO recommendRequestDTO);

    public String getCommonTimePrefix4Msg();

}
