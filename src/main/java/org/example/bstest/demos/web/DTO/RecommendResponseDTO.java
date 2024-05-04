package org.example.bstest.demos.web.DTO;

import lombok.Builder;
import lombok.Data;
import org.example.bstest.demos.web.entity.AgentEntity;
import org.example.bstest.demos.web.entity.SortedAgentEntity;
import org.example.bstest.demos.web.enums.ResponseStatusEnum;

import java.util.List;

@Data
@Builder
public class RecommendResponseDTO {




    List<AgentEntity> agentEntityList;

//    被过滤经纪人信息
    List<SortedAgentEntity> sortedAgentEntityList;

    ResponseStatusEnum responseStatusEnum;

    int code;

    String msg;

    Boolean isBackstop;

    List<String> traceLog;

    RecommendResponseDTO recommendResponseDTO;

    boolean isThroughCache;

    @Override
    public String toString() {
        return "RecommendResponseDTO{" +
                "agentEntityList=" + agentEntityList +
                ", sortedAgentEntityList=" + sortedAgentEntityList +
                ", responseStatusEnum=" + responseStatusEnum +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", isBackstop=" + isBackstop +
                ", traceLog=" + traceLog +
                '}';
    }
}
