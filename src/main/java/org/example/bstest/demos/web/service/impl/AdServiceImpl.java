package org.example.bstest.demos.web.service.impl;

import org.example.bstest.demos.web.mapper.mysql.Ad2StrategyMapper;
import org.example.bstest.demos.web.service.AdService;
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
