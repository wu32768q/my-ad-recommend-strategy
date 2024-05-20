package org.example.bstest.demos.bishe.controller;


import org.example.bstest.demos.bishe.DTO.RecommendRequestDTO;
import org.example.bstest.demos.bishe.DTO.RecommendResponseDTO;
import org.example.bstest.demos.bishe.proxy.CaffieneCacheProxy;
import org.example.bstest.demos.bishe.service.RecommendPreService;
import org.example.bstest.demos.bishe.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecommendController {

    @Autowired
    RecommendService recommendService;

    @Autowired
    CaffieneCacheProxy caffieneCacheProxy;

    @Autowired
    RecommendPreService recommendPreService;


    @GetMapping("/recommend")
    @ResponseBody
    public RecommendResponseDTO addElement(@RequestParam String tableName2Recall,
                                           @RequestParam String strategyId,
                                           @RequestParam @Nullable String adId,
                                           @RequestParam int expectNumber,
                                           @RequestParam boolean traceLogSwitch) {

        RecommendRequestDTO recommendRequestDTO = recommendService
                .buildInitRecommendRequest( tableName2Recall, strategyId, adId, expectNumber, traceLogSwitch);


//        return recommendService.doRecommend(recommendRequestDTO);
        return caffieneCacheProxy.doRecommendProxy(recommendRequestDTO);
    }
}
