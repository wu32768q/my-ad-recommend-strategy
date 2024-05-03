package org.example.bstest.demos.web.service.impl;

import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.proxy.CaffieneCacheProxy;
import org.example.bstest.demos.web.service.RecommendPreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



@Service
public class RecommendPreServiceImpl implements RecommendPreService {

    @Autowired
    CaffieneCacheProxy caffieneCacheProxy;


    @Override
    public void fillStrategyIdByAdId(String  adId) {
        //        策略id优先，为空才考虑展位id
//        if( ! StringUtils.hasText(adId)) {
//            String  strategyId = caffieneCacheProxy.doGetStrategyIdProxy(recommendRequestDTO.getAdId());
//            recommendRequestDTO.setStrategyId(strategyId);
//        }
    }
}
