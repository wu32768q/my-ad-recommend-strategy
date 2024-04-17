package org.example.bstest.demos.web;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class StrategyDTO {

    //各组件id
    List<Integer> list;

    //策略名
    String strategyName;

    //策略id
    Integer strategyId;



}
