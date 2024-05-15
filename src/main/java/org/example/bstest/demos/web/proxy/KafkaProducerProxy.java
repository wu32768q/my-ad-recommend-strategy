package org.example.bstest.demos.web.proxy;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

@Component
public class KafkaProducerProxy {

    @Resource
    KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topicName, String msg) {
//        System.out.println("kafka topicName：" + topicName + ", Send Msg is : " + msg);
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName, msg);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                System.out.println(topicName + " - 生产者 发送消息失败：" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                System.out.println(topicName + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });

    }


}
