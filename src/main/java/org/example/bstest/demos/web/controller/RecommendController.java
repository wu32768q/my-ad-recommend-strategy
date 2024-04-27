package org.example.bstest.demos.web.controller;


import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecommendController {

    @Autowired
    RecommendService recommendService;


    @GetMapping("/recommend")
    @ResponseBody
    public RecommendResponseDTO addElement(@RequestBody RecommendRequestDTO recommendRequestDTO) {

        return recommendService.doRecommend(recommendRequestDTO);
    }
}
