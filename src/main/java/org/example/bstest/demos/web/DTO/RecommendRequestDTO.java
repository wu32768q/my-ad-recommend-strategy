package org.example.bstest.demos.web.DTO;

import lombok.Builder;
import lombok.Data;
import org.example.bstest.demos.web.decorators.AbstractElementDecorator;
import org.example.bstest.demos.web.element.AbstractElement;

import java.util.Objects;

@Data
@Builder
public class RecommendRequestDTO {

    String adId;

    String strategyId;

    int expectNumber;

    String tableName2Recall;

    String channel;

//    int hashCode;

//    使用的装饰器对象
    Class<? extends AbstractElementDecorator> decoratorClass;

    boolean traceLogSwitch;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendRequestDTO that = (RecommendRequestDTO) o;
        return expectNumber == that.expectNumber && Objects.equals(adId, that.adId) && Objects.equals(strategyId, that.strategyId) && Objects.equals(tableName2Recall, that.tableName2Recall) && Objects.equals(channel, that.channel) && Objects.equals(decoratorClass, that.decoratorClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adId, strategyId, expectNumber, tableName2Recall, channel, decoratorClass);
//        return hashCode;
    }
}
