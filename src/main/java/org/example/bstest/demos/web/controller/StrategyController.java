

package org.example.bstest.demos.web.controller;

import org.example.bstest.demos.web.StrategyDTO;
import org.example.bstest.demos.web.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StrategyController {

    @Autowired
    StrategyService strategyService;


//    增
    @PostMapping("/Strategy")
    @ResponseBody
    public Integer addStrategy(@RequestBody StrategyDTO strategyDTO) {
        strategyService.addStrategy(strategyDTO);
        return 200;
    }

//    删
    @DeleteMapping("/Strategy")
    @ResponseBody
    public Boolean deleteStrategy(@RequestParam Integer strategyId) {
        return strategyService.deleteStrategy(strategyId);
    }

//    改
    @PutMapping("/Strategy")
    @ResponseBody
    public Boolean updateStrategy(@RequestBody StrategyDTO strategyDTO) {
        return strategyService.updateStrategy(strategyDTO);
    }

//    查
    @GetMapping("/StratrgyList")
    @ResponseBody
    public List<StrategyDTO> getStrategyList() {
        return strategyService.getStrategyList();
    }

}
