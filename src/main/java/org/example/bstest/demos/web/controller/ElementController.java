package org.example.bstest.demos.web.controller;

import org.example.bstest.demos.web.ElementDTO;
import org.example.bstest.demos.web.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ElementController {

    @Autowired
    ElementService elementService;


    //    增
    @PostMapping("/Element")
    @ResponseBody
    public Integer addElement(@RequestBody ElementDTO elementDTO) {
        elementService.addElement(elementDTO);
        return 200;
    }

    //    删
    @DeleteMapping("/Element")
    @ResponseBody
    public Boolean deleteElement(@RequestParam Integer elementId) {
        return elementService.deleteElement(elementId);
    }

    //    改
    @PutMapping("/Element")
    @ResponseBody
    public Boolean updateElement(@RequestBody ElementDTO elementDTO) {
        return elementService.updateElement(elementDTO);
    }

    //    查
    @GetMapping("/ElementList")
    @ResponseBody
    public List<ElementDTO> getElementList() {
        return elementService.getElementList();
    }

}
