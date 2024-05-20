package org.example.bstest.demos.bishe.service;

import org.example.bstest.demos.bishe.DTO.RouteResponseDTO;
import org.example.bstest.demos.bishe.DTO.StrategyDTO;
import org.example.bstest.demos.bishe.entity.StrategyEntity;

import java.util.List;

public interface StrategyService {

    public RouteResponseDTO<List<StrategyEntity>> getStrategyList();
    public RouteResponseDTO insertStrategy(StrategyEntity strategyEntity);
    public RouteResponseDTO deleteStrategy(String strategyId);
    public RouteResponseDTO updateStrategy(StrategyEntity strategyEntity);
    public RouteResponseDTO<StrategyEntity> getStrategyById(String id);
    RouteResponseDTO<List<StrategyDTO>> entity2DtoHandle(RouteResponseDTO<List<StrategyEntity>> dtoOfEntity2Handle);


}
