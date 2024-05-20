package org.example.bstest.demos.bishe.service.impl;


import org.example.bstest.demos.bishe.entity.elementEntity.ElementEntity;
import org.example.bstest.demos.bishe.mapper.mongodb.ElementMapper;
import org.example.bstest.demos.bishe.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementServiceImpl implements ElementService {

    @Autowired
    ElementMapper elementMapper;

    @Override
    public List<ElementEntity> getElementList() {
        return elementMapper.getElementList();
    }

    @Override
    @Caching
    public ElementEntity getElementById(String id) {
        return elementMapper.getElementById(id);
    }

    @Override
    public Boolean addElement(ElementEntity elementEntity) {
        elementMapper.insertElement(elementEntity);
        return true;
    }

    @Override
    public Boolean deleteElement(Integer elementId) {
        return null;
    }

    @Override
    public Boolean updateElement(ElementEntity elementEntity) {
        return null;
    }
}
