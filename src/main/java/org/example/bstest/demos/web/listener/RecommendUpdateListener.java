package org.example.bstest.demos.web.listener;

import org.example.bstest.demos.web.constants.KafkaConstants;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

public class RecommendUpdateListener {
//    ,containerFactory = "batchFactory"
    @KafkaListener(topics = {KafkaConstants.RECOMMEND_COUNT_INC_TOPIC})
    public void consumer(List<String> msgList){
        System.out.println("list size:" + msgList.size());
        msgList.forEach(System.out::println);

    }

}
