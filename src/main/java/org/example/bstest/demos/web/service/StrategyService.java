package org.example.bstest.demos.web.service;

import org.example.bstest.demos.web.StrategyDTO;

import java.util.List;

public interface StrategyService {

    public List<StrategyDTO> getStrategyList();
    public Boolean addStrategy(StrategyDTO strategyDTO);
    public Boolean deleteStrategy(Integer strategyId);
    public Boolean updateStrategy(StrategyDTO strategyDTO);

}
