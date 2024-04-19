package org.example.bstest.demos.web.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgentEntity {

    @NonNull
    String id;

    String name;

    int age;

    String subject;

    String cityCode;

    String cityName;

    int workingYears;

    int level;




}
