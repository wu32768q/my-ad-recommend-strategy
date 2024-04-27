package org.example.bstest.demos.web.service;

import org.example.bstest.demos.web.entity.elementEntity.ElementEntity;

import java.util.List;

public interface ElementService {

    public List<ElementEntity> getElementList();
    public ElementEntity getElementById(String id);
    public Boolean addElement(ElementEntity elementEntity);
    public Boolean deleteElement(Integer elementId);
    public Boolean updateElement(ElementEntity elementEntity);

}
