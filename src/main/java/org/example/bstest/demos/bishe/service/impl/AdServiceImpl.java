package org.example.bstest.demos.bishe.service.impl;

import org.example.bstest.demos.bishe.mapper.mysql.Ad2StrategyMapper;
import org.example.bstest.demos.bishe.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdServiceImpl implements AdService {

    @Autowired
    Ad2StrategyMapper ad2StrategyMapper;
    @Override
    public String getStrategyIdByAdId(String AdId) {
        return ad2StrategyMapper.getStrategyIdByAdId(AdId);
    }

}
