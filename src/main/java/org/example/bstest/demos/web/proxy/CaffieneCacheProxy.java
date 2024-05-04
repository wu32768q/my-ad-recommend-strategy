package org.example.bstest.demos.web.proxy;

import com.alibaba.druid.util.StringUtils;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.constants.MsgConstants;
import org.example.bstest.demos.web.service.AdService;
import org.example.bstest.demos.web.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class CaffieneCacheProxy {

    @Autowired
    RecommendService recommendService;

    @Autowired
    AdService adService;


    Cache<Integer , RecommendResponseDTO> cache4Agent;
    Cache<String , String> cache4Ad2Strategy;

    @PostConstruct
    public void init() {
        cache4Agent = Caffeine.newBuilder()
                //初始数量
                .initialCapacity(10)
                //最大条数
                .maximumSize(20)
                //最后一次读或写操作后经过指定时间过期
                .expireAfterAccess(10, TimeUnit.SECONDS)
                //监听缓存被移除
//            .removalListener((key, val, removalCause) -> { })
                //记录命中
//            .recordStats()
                .build();
//            key -> recommendService.doRecommend(key)



//        cache4Ad2Strategy = Caffeine.newBuilder()
//                //初始数量
//                .initialCapacity(10)
//                //最大条数
//                .maximumSize(20)
//                .expireAfterAccess(1000, TimeUnit.MICROSECONDS)
//                .build();


    }


    public RecommendResponseDTO doRecommendProxy(RecommendRequestDTO recommendRequestDTO) {
//        Map<Integer, RecommendResponseDTO> map = cache4Agent.asMap();
//        map.forEach((k,v)->System.out.println(k.toString() + " " + v.toString()));
        Random random = new Random();
        int hashCode = recommendRequestDTO.hashCode();
        RecommendResponseDTO recommendResponseDTO = cache4Agent.getIfPresent(hashCode);
        if(!ObjectUtils.isEmpty(recommendResponseDTO)) {
//            && random.nextInt(5) > 1
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(recommendRequestDTO.isTraceLogSwitch()) {
                return RecommendResponseDTO.builder()
                        .recommendResponseDTO(recommendResponseDTO)
                        .isThroughCache(true)
                        .build();
            }

            return recommendResponseDTO;
        }
        recommendResponseDTO = recommendService.doRecommend(recommendRequestDTO);
        cache4Agent.put(hashCode, recommendResponseDTO);
         return recommendResponseDTO;

    }


//    public String doGetStrategyIdProxy(String adId) {
//        String strategyId = cache4Ad2Strategy.getIfPresent(adId);
//        if( ! StringUtils.isEmpty(strategyId)) {
//            return strategyId;
//        }
//        strategyId = adService.getStrategyIdByAdId(adId);
//        cache4Ad2Strategy.put(adId, strategyId);
//        return strategyId;
//
//    }
}
