package org.example.bstest.demos.web.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.example.bstest.demos.web.entity.ElementEntity;

import java.util.List;

@Mapper
public interface ElementMapper {
    @Insert("insert into ")
    int addElement();
    Boolean deleteElement();
    Boolean updateElement();
    List<ElementEntity> getElementList();

    ElementEntity getElementById(String id);

}
