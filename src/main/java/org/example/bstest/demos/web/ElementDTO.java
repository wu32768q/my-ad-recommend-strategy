package org.example.bstest.demos.web;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ElementDTO {


    //组件名
    String strategyName;

    //组件id
    Integer strategyId;



}
