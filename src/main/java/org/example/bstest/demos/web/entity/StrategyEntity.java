package org.example.bstest.demos.web.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("strategy_collection")
public class StrategyEntity {

    @Field("element_list")
    //各组件id
    List<String> elementList;


    //策略名
    @Field("strategy_name")
    String strategyName;

    //策略中文名
    @Field("strategy_chinese_name")
    String strategyChineseName;

    //策略id
    @MongoId
    ObjectId strategyId;

    //策略描述
    @Field("description")
    @JsonProperty("strategyDescription")
    String description;

    @Field("create_time")
    String createTime;

    public Map<String, Object> getAllNotNullFieldMap() {
        java.lang.reflect.Field fields[] = this.getClass().getDeclaredFields();
        Map<String, Object> map = new HashMap<>();
        for (java.lang.reflect.Field field : fields) {
            field.setAccessible(true);
            try {
                if(Objects.isNull(field.get(this))) {
                    continue;
                }
                map.put(field.getName(), field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;

    }




    @Override
    public String toString() {
        return "StrategyEntity{" +
                "elementList=" + elementList +
                ", strategyName='" + strategyName + '\'' +
                ", strategyChineseName='" + strategyChineseName + '\'' +
                ", strategyId=" + strategyId +
                ", description='" + description + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }



}
