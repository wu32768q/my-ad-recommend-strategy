package org.example.bstest.demos.bishe.service;

import org.example.bstest.demos.bishe.DTO.RecommendRequestDTO;
import org.example.bstest.demos.bishe.DTO.RecommendResponseDTO;
import org.example.bstest.demos.bishe.entity.AgentEntity;
import org.example.bstest.demos.bishe.entity.elementEntity.ElementEntity;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface RecommendService {

    public RecommendResponseDTO doRecommend(RecommendRequestDTO recommendRequestDTO);

    public void fillStrategyIdByAdId(RecommendRequestDTO recommendRequestDTO);

    public  RecommendResponseDTO buildInitRecommeendResponse();

    public Boolean isResEnough(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO);

    public void backstopHandle(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) throws InstantiationException, IllegalAccessException;

    public Boolean requestCheck(RecommendRequestDTO recommendRequestDTO);

    public void buildFinalResponse(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, Boolean flag, String msg);

    public void fillRequeset(RecommendRequestDTO recommendRequestDTO);

    public String getCommonTimePrefix4Msg();

    public void limitAgentList(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO);

    @Async
    void updateRecommendCountByAgentList(List<AgentEntity> agentList, RecommendRequestDTO recommendRequestDTO);

//    public RecommendRequestDTO buildInitRecommendRequest(String tableName2Recall,String strategyId, String adId, int expectNumber);

    RecommendRequestDTO buildInitRecommendRequest(String tableName2Recall, String strategyId, String adId, int expectNumber, boolean traceLogSwitch);

    RecommendResponseDTO fallBack4DoRecommend(RecommendRequestDTO recommendRequestDTO);
}
