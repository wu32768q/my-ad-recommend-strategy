package org.example.bstest.demos.bishe.service;

import org.example.bstest.demos.bishe.entity.AgentEntity;

import java.util.List;

public interface AgentService {

    public boolean checkatableName(String tableName);

    public List<AgentEntity> getAgentWholeTable(String tableName);



}
