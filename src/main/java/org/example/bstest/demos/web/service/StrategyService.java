package org.example.bstest.demos.web.service;

import org.example.bstest.demos.web.DTO.RouteResponseDTO;
import org.example.bstest.demos.web.DTO.StrategyDTO;
import org.example.bstest.demos.web.entity.StrategyEntity;

import java.util.List;

public interface StrategyService {

    public RouteResponseDTO<List<StrategyEntity>> getStrategyList();
    public RouteResponseDTO insertStrategy(StrategyEntity strategyEntity);
    public RouteResponseDTO deleteStrategy(String strategyId);
    public RouteResponseDTO updateStrategy(StrategyEntity strategyEntity);
    public RouteResponseDTO<StrategyEntity> getStrategyById(String id);
    RouteResponseDTO<List<StrategyDTO>> entity2DtoHandle(RouteResponseDTO<List<StrategyEntity>> dtoOfEntity2Handle);


}
