package org.example.bstest.demos.web.DTO;

import lombok.Builder;
import lombok.Data;
import org.example.bstest.demos.web.decorators.AbstractElementDecorator;
import org.example.bstest.demos.web.element.AbstractElement;

@Data
@Builder
public class RecommendRequestDTO {

    String adId;

    String strategyId;

    int expectNumber;

    String tableName2Recall;

    String channel;

//    使用的装饰器对象
    Class<? extends AbstractElementDecorator> decoratorClass;


    @Override
    public String toString() {
        return "RecommendRequestDTO{" +
                "adId='" + adId + '\'' +
                ", strategyId='" + strategyId + '\'' +
                ", expectNumber=" + expectNumber +
                ", tableName2Recall='" + tableName2Recall + '\'' +
                ", channel='" + channel + '\'' +
                ", decoratorClass=" + decoratorClass +
                '}';
    }
}
