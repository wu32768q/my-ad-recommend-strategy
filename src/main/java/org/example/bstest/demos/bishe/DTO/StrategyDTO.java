package org.example.bstest.demos.bishe.DTO;

import lombok.Builder;
import lombok.Data;
import org.example.bstest.demos.bishe.entity.StrategyEntity;

import java.util.List;

@Builder
@Data
public class StrategyDTO {


    //各组件id
    List<String> elementList;


    //策略名
    String strategyName;

    //策略中文名
    String strategyChineseName;

    //策略id
    String strategyId;

    //策略描述
    String strategyDescription;

    String createTime;


    public static StrategyDTO fromEntity(StrategyEntity strategyEntity) {
        return StrategyDTO.builder()
                .elementList(strategyEntity.getElementList())
                .strategyName(strategyEntity.getStrategyName())
                .strategyChineseName(strategyEntity.getStrategyChineseName())
                .strategyId(strategyEntity.getStrategyId().toString())
                .strategyDescription(strategyEntity.getDescription())
                .createTime(strategyEntity.getCreateTime())
                .build();
    }


}
