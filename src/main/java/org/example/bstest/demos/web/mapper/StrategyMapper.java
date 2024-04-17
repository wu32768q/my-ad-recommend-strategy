package org.example.bstest.demos.web.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.example.bstest.demos.web.StrategyDTO;

import java.util.List;

@Mapper
public interface StrategyMapper {
    Boolean addStrategy();
    Boolean deleteStrategy();
    Boolean updateStrategy();
    List<StrategyDTO> getStrategyList();

}
