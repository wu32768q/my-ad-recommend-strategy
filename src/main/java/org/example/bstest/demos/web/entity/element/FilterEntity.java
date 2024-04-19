package org.example.bstest.demos.web.entity.element;

import org.example.bstest.demos.web.entity.ElementEntity;
import org.example.bstest.demos.web.enums.FilterTypeEnum;

public class FilterEntity extends ElementEntity {

    //使用的表名
    String tableName;

    //根据字段过滤场景，使用的表内字段
    String formName;

    FilterTypeEnum filterTypeEnum;



}
