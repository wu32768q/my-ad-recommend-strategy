package org.example.bstest.demos.bishe.proxy;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.util.concurrent.RateLimiter;
import org.example.bstest.demos.bishe.DTO.RecommendRequestDTO;
import org.example.bstest.demos.bishe.DTO.RecommendResponseDTO;
import org.example.bstest.demos.bishe.service.AdService;
import org.example.bstest.demos.bishe.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class CaffieneCacheProxy {

    @Autowired
    RecommendService recommendService;

    @Autowired
    AdService adService;

    RateLimiter rateLimiter;


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
                .expireAfterAccess(5, TimeUnit.SECONDS)
                //监听缓存被移除
//            .removalListener((key, val, removalCause) -> { })
                //记录命中
//            .recordStats()
                .build();
//            key -> recommendService.doRecommend(key)

        rateLimiter = RateLimiter.create(1000);

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
        if(!rateLimiter.tryAcquire()) {
            return RecommendResponseDTO.builder()
                    .isThroughCache(Boolean.FALSE)
                    .msg("未拿到令牌，请稍后重试")
                    .code(-2)
                    .isBackstop(Boolean.FALSE)
                    .agentEntityList(Collections.EMPTY_LIST)
                    .sortedAgentEntityList(Collections.EMPTY_LIST)
                    .build();

        }
//        System.out.println("缓存未命中");
        recommendResponseDTO = recommendService.doRecommend(recommendRequestDTO);
//        二重校验
        if(ObjectUtils.isEmpty(cache4Agent.getIfPresent(hashCode))) {
            cache4Agent.put(hashCode, recommendResponseDTO);
        }
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
