package org.example.bstest.demos.web.service;

import org.example.bstest.demos.web.entity.AgentEntity;

import java.util.List;

public interface AgentService {

    public boolean checkatableName(String tableName);

    public List<AgentEntity> getAgentWholeTable(String tableName);

}
