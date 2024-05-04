package org.example.bstest.demos.web.mapper.mysql;

import org.apache.ibatis.annotations.*;
import org.example.bstest.demos.web.entity.AgentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


//经纪人物料mapper
@Mapper
public interface AgentMaterialMapper {


    @Select("select  * from ${tableName} ")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "age", property = "age"),
            @Result(column = "recommend_count", property = "recommendCount"),
            @Result(column = "quality_value", property = "qualityValue"),
            @Result(column = "working_years", property = "workingYears"),
            @Result(column = "city_code", property = "cityCode"),
            @Result(column = "city_name", property = "cityName"),
    })
    public List<AgentEntity> getAllMsgInTable(String tableName);

    @Select("SELECT COUNT(*)   \n" +
            "FROM INFORMATION_SCHEMA.TABLES   \n" +
            "WHERE table_schema = 'mydb'   \n" +
            "AND table_name = ${tableName};")
    public int tableNameCheck(String tableName);


    @Update("update ${tableName}  set recommend_count = ${recommendCount + 1} \n" +
            "WHERE id = ${id};")
    public void increaseRecommendCountById(@Param("id")String id,
                                           @Param("tableName") String tableName,
                                           @Param("recommendCount") int recommendCount);

    @Select("select recommend_count from ${tableName}  where id = ${id}")
    public int getRecommendCountById(@Param("id")String id,
                                     @Param("tableName") String tableName);


}
