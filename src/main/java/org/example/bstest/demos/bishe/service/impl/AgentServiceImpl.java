package org.example.bstest.demos.bishe.service.impl;

import org.example.bstest.demos.bishe.entity.AgentEntity;
import org.example.bstest.demos.bishe.mapper.mysql.AgentMaterialMapper;
import org.example.bstest.demos.bishe.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class AgentServiceImpl implements AgentService {


    @Autowired
    AgentMaterialMapper agentMaterialMapper;


    @Override
    public boolean checkatableName(String tableName) {

        int count = agentMaterialMapper.tableNameCheck(tableName);
        return count == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public List<AgentEntity> getAgentWholeTable(String tableName) {
        return agentMaterialMapper.getAllMsgInTable(tableName);
    }
}
