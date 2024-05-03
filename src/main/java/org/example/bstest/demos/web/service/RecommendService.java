package org.example.bstest.demos.web.service;

import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.DTO.RouteResponseDTO;
import org.example.bstest.demos.web.entity.elementEntity.ElementEntity;

public interface RecommendService {

    public RecommendResponseDTO doRecommend(String tableName2Recall,String strategyId, String adId, int expectNumber);

    public void fillStrategyIdByAdId(RecommendRequestDTO recommendRequestDTO);

    public  RecommendResponseDTO buildInitRecommeendResponse();

    public Boolean isResEnough(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO);

    public void backstopHandle(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) throws InstantiationException, IllegalAccessException;

    public Boolean requestCheck(RecommendRequestDTO recommendRequestDTO);

    public void buildFinalResponse(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, Boolean flag, String msg);

    public void fillRequeset(RecommendRequestDTO recommendRequestDTO);

    public String getCommonTimePrefix4Msg();

    public void limitAgentList(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO);

}
