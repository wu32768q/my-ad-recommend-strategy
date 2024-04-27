package org.example.bstest.demos.web.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgentEntity {


    String id;

    String name;

    int age;

    String channel;

    String cityCode;

    String cityName;

    int workingYears;

    int level;
//    经纪人q值
    int qualityValue;

    //被推荐次数统计
    int recommendCount;






}
