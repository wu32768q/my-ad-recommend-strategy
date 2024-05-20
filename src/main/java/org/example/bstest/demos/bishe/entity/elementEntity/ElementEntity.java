package org.example.bstest.demos.bishe.entity.elementEntity;


import lombok.*;
import org.example.bstest.demos.bishe.enums.ElementTypeEnum;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("element_collection")
public class ElementEntity {


    //组件名
    @Field("element_name")
    String elementName;

    //组件中文名
    @Field("element_chinese_name")
    String elementChineseName;

    //组件id
    @Field("element_id")
    String elementId;

    //组件描述
    @Field("description")
    String description;

    //组件类型
    @Field("element_type_enum")
    ElementTypeEnum elementTypeEnum;


    @Field("table_name")
    String tableName;

    //根据字段过滤场景，使用的表内字段
    @Field("column_name")
    String columnName;

    @Field("left_rule")
    int leftRule;

    @Field("right_rule")
    int rightRule;

    @Field("expect_column_value")
    String expectColumnValue;


//
//    FilterTypeEnum filterTypeEnum;


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


}
