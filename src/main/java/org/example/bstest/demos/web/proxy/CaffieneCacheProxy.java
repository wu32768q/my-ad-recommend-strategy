package org.example.bstest.demos.web.proxy;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.constants.MsgConstants;
import org.example.bstest.demos.web.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class CaffieneCacheProxy {

    @Autowired
    RecommendService recommendService;


    Cache<Integer , RecommendResponseDTO> cache;

    @PostConstruct
    public void init() {
        cache = Caffeine.newBuilder()
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

    }


    public RecommendResponseDTO doCaffieneProxy(RecommendRequestDTO recommendRequestDTO) {
//        Map<Integer, RecommendResponseDTO> map = cache.asMap();
//        map.forEach((k,v)->System.out.println(k.toString() + " " + v.toString()));
        int hashCode = recommendRequestDTO.hashCode();
        RecommendResponseDTO recommendResponseDTO = cache.getIfPresent(hashCode);
//        System.out.println(recommendRequestDTO.hashCode() + " " + recommendResponseDTO);

        if(!ObjectUtils.isEmpty(recommendResponseDTO)) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return recommendResponseDTO;
        }

        recommendResponseDTO = recommendService.doRecommend(recommendRequestDTO);
        cache.put(hashCode, recommendResponseDTO);
        recommendResponseDTO.getTraceLog().add(recommendService.getCommonTimePrefix4Msg() + MsgConstants.BUILD_BY_CACHE);
        return recommendResponseDTO;

    }


}
