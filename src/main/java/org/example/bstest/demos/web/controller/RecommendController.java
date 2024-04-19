package org.example.bstest.demos.web.controller;


import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecommendController {

    @GetMapping("/recommend")
    @ResponseBody
    public RecommendResponseDTO addElement(@RequestBody RecommendRequestDTO recommendRequestDTO) {
        return new RecommendResponseDTO();
    }
}
