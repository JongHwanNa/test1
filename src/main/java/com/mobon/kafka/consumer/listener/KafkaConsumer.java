package com.mobon.kafka.consumer.listener;

import com.mobon.kafka.consumer.model.CookieVo;
import com.mobon.kafka.core.ConsumerProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    //ConsumerProcess consumerProcess = new ConsumerProcess();

    @Autowired
    public static ConsumerProcess consumerProcess;

    @KafkaListener(topics = "mobon-cookiestore", group = "group-mobon-cookiestore", containerFactory = "userKafkaListenerFactory")
    public void consumeJson(CookieVo cookieVo) {

        String name = cookieVo.getName();
        String value = "morla";
        cookieVo.setValue(value);
        Long expire = 12312123L;
        cookieVo.setExpire(expire);

        System.out.println("Consumed JSON Message: " + name);

        consumerProcess.processMain(cookieVo);
    }

}
