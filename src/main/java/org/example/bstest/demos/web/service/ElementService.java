package org.example.bstest.demos.web.service;

import org.example.bstest.demos.web.ElementDTO;

import java.util.List;

public interface ElementService {

    public List<ElementDTO> getElementList();
    public Boolean addElement(ElementDTO elementDTO);
    public Boolean deleteElement(Integer elementId);
    public Boolean updateElement(ElementDTO elementDTO);

}
