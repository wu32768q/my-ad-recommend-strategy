package org.example.bstest.demos.web.mapper.mysql;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.bstest.demos.web.entity.AgentEntity;

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


}
