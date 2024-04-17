package org.example.bstest.demos.web.service.impl;


import org.example.bstest.demos.web.ElementDTO;
import org.example.bstest.demos.web.service.ElementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementServiceImpl implements ElementService {

    @Override
    public List<ElementDTO> getElementList() {
        return null;
    }

    @Override
    public Boolean addElement(ElementDTO elementDTO) {
        return null;
    }

    @Override
    public Boolean deleteElement(Integer elementId) {
        return null;
    }

    @Override
    public Boolean updateElement(ElementDTO elementDTO) {
        return null;
    }
}
