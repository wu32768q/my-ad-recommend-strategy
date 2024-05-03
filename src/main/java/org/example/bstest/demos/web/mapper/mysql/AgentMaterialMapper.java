package org.example.bstest.demos.web.mapper.mysql;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.bstest.demos.web.entity.AgentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


//经纪人物料mapper
@Mapper
public interface AgentMaterialMapper {


    @Select("select  * from ${tableName} ")
    public List<AgentEntity> getAllMsgInTable(String tableName);

    @Select("SELECT COUNT(*)   \n" +
            "FROM INFORMATION_SCHEMA.TABLES   \n" +
            "WHERE table_schema = 'mydb'   \n" +
            "AND table_name = ${tableName};")
    public int tableNameCheck(String tableName);


    @Update("update ${tableName}  set recommend_count = ${recommendCount + 1} \n" +
            "WHERE id = ${id};")
    public void increaseRecommendCountById(String id, String tableName, int recommendCount);

    @Select("select recommend_count from ${tableName}  where id = ${id}")
    public int getRecommendCountById(String id, String tableName);


}
