package org.example.bstest.demos.web.entity;


import lombok.*;
import org.example.bstest.demos.web.enums.ElementTypeEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ElementEntity {


    //组件名
    String strategyName;

    //组件中文名
    String strategyChineseName;

    //组件id
    Integer elementId;

    //组件描述
    String description;

    //组件类型
    ElementTypeEnum elementTypeEnum;





}
