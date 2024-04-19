

package org.example.bstest.demos.web.controller;

import org.example.bstest.demos.web.DTO.RouteResponseDTO;
import org.example.bstest.demos.web.entity.StrategyEntity;
import org.example.bstest.demos.web.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StrategyController {

    @Autowired
    StrategyService strategyService;


//    增
    @PostMapping("/strategy")
    @ResponseBody
    public RouteResponseDTO addStrategy(@RequestBody StrategyEntity strategyEntity) {
        return strategyService.insertStrategy(strategyEntity);

    }

//    删
    @DeleteMapping("/strategy")
    @ResponseBody
    public RouteResponseDTO deleteStrategy(@RequestParam String strategyId) {
        return strategyService.deleteStrategy(strategyId);
    }

//    改
    @PutMapping("/strategy")
    @ResponseBody
    public RouteResponseDTO updateStrategy(@RequestBody StrategyEntity strategyEntity) {
        return strategyService.updateStrategy(strategyEntity);
    }

//    查
    @GetMapping("/stratrgyList")
    @ResponseBody
    public RouteResponseDTO<List<StrategyEntity>> getStrategyList() {
        return strategyService.getStrategyList();
    }

}
