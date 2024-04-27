package org.example.bstest.demos.web.DTO;

import lombok.Builder;
import lombok.Data;
import org.example.bstest.demos.web.decorators.AbstractElementDecorator;
import org.example.bstest.demos.web.element.AbstractElement;

@Data
@Builder
public class RecommendRequestDTO {

    String AdId;

    String StrategyId;

    int expectNumber;

    String tableName2Recall;

    String channel;

//    使用的装饰器对象
    Class<? extends AbstractElementDecorator> decoratorClass;



}
