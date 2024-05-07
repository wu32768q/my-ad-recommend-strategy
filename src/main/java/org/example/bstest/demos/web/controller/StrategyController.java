

package org.example.bstest.demos.web.controller;

import org.example.bstest.demos.web.DTO.RouteResponseDTO;
import org.example.bstest.demos.web.DTO.StrategyDTO;
import org.example.bstest.demos.web.entity.StrategyEntity;
import org.example.bstest.demos.web.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
public class StrategyController {

    @Autowired
    StrategyService strategyService;
    @Autowired
    HttpServletRequest request;


//    增
    @CrossOrigin(origins = "http://localhost:9526/")
    @PostMapping("/strategy")
    @ResponseBody
    public RouteResponseDTO addStrategy(@RequestBody StrategyEntity strategyEntity) {
        RouteResponseDTO routeResponseDTO = strategyService.insertStrategy(strategyEntity);
        return routeResponseDTO;
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
    @GetMapping("/strategy/list")
    @ResponseBody
    public RouteResponseDTO<List<StrategyDTO>> getStrategyList() {
        RouteResponseDTO<List<StrategyEntity>>  dtoOfEntity2Handle = strategyService.getStrategyList();
        return strategyService.entity2DtoHandle(dtoOfEntity2Handle);

    }

}
