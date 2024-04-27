package org.example.bstest.demos.web.service.impl;

import org.example.bstest.demos.web.entity.AgentEntity;
import org.example.bstest.demos.web.mapper.mysql.AgentMaterialMapper;
import org.example.bstest.demos.web.service.AgentService;
import org.example.bstest.demos.web.utils.MyStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
