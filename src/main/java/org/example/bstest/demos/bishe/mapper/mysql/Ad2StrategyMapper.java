package org.example.bstest.demos.bishe.mapper.mysql;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Ad2StrategyMapper {

    @Select("select strategy_id from ad_to_strategy_table where ad_id = ${adId}")
    public String getStrategyIdByAdId(String adId);

}
