package org.example.bstest.demos.web.controller;

import org.example.bstest.demos.web.entity.ElementEntity;
import org.example.bstest.demos.web.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ElementController {

    @Autowired
    ElementService elementService;


    //    增
    @PostMapping("/element")
    @ResponseBody
    public Integer addElement(@RequestBody ElementEntity elementEntity) {
        elementService.addElement(elementEntity);
        return 200;
    }

    //    删
    @DeleteMapping("/element")
    @ResponseBody
    public Boolean deleteElement(@RequestParam Integer elementId) {
        return elementService.deleteElement(elementId);
    }

    //    改
    @PutMapping("/element")
    @ResponseBody
    public Boolean updateElement(@RequestBody ElementEntity elementEntity) {
        return elementService.updateElement(elementEntity);
    }

    //    查
    @GetMapping("/elementList")
    @ResponseBody
    public List<ElementEntity> getElementList() {
        return elementService.getElementList();
    }

}
