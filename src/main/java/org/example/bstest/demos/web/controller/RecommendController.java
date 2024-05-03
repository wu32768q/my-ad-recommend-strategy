package org.example.bstest.demos.web.controller;


import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.proxy.CaffieneCacheProxy;
import org.example.bstest.demos.web.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecommendController {

    @Autowired
    RecommendService recommendService;

    @Autowired
    CaffieneCacheProxy caffieneCacheProxy;


    @GetMapping("/recommend")
    @ResponseBody
    public RecommendResponseDTO addElement(@RequestParam String tableName2Recall,
                                           @RequestParam String strategyId,
                                           @RequestParam @Nullable String adId,
                                           @RequestParam int expectNumber) {

        RecommendRequestDTO recommendRequestDTO = recommendService.buildInitRecommendRequest( tableName2Recall, strategyId, adId, expectNumber);


//        return recommendService.doRecommend(recommendRequestDTO);
        return caffieneCacheProxy.doCaffieneProxy(recommendRequestDTO);
    }
}
