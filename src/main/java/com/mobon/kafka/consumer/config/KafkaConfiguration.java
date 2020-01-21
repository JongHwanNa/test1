package com.mobon.kafka.consumer.config;

import com.mobon.kafka.consumer.model.CookieVo;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@PropertySource("classpath:config.properties")
@Configuration
public class KafkaConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConfiguration.class);

    @Autowired
    ApplicationArguments applicationArguments;

    //@Value("${KAFKA_SERVICE}") String KAFKA_SERVICE;

    //카프카 클러스터에 처음 연결을 하기 위한 호스트와 포트 정보로 구성된 리스트 정보(브로커 정보) bootstrap.servers
    @Value("${KAFKA_SERVICE_HOST}") String HOST;
    //컨슈머가 속한 컨슈머 그룹을 식별하는 식별자이다. group.id
    @Value("${KAFKA_SERVICE_GROUP_ID}") String GROUP_ID;
    //백그라운드에서 주기적으로 오프셋을 자동 커밋한다. enable.auto.commit
    @Value("${KAFKA_SERVICE_AUTO_COMMIT}") String AUTO_COMMIT;
    //요청에 대해 응답을 기다리는 최대 시간. auto.commit.interval.ms
    @Value("${KAFKA_SERVICE_INTERVAL_MS}") String INTERVAL_MS;
    /*
     *컨슈머와 브로커사이의 세션 타임 아웃시간. 브로커가 컨슈머가 살아있는 것으로 판단하는 시간(기본값 10초) 만약 컨슈머가 그룹 코디네이터에게 하트비트를 보내지 않고
     * session.timeout.ms이 지나면 해당 컨슈머는 종료되거나 장애가 발생한 것으로 판단하고 컨슈머 그룹은 리밸런스(rebalance)를 시도한다.
     * session.timeout.ms는 하트비트 없이 얼마나 오랫동안 컨슈머가 있을 수 있는지를 제어하는 시간이며, 이 속성은 heartbeat.interval.ms와 밀접한 관련이 있다.
     * session.timeout.ms를 기본값보다 낮게 설정하면 실패를 빨리 감지 할 수 있지만, GC나 poll 루프를 완료하는 시간이 길어지게 되면 원하지 않게 리밸런스가 일어나기도 한다.
     * 반대로는 리밸런스가 일어날 가능성은 줄지만 실제 오류를 감지하는 데 시간이 오래 걸릴 수 있다
     */
    @Value("${KAFKA_SERVICE_TIMEOUT_MS}") String TIMEOUT_MS;
    //단일 호출 poll()에 대한 최대 레코드 수를 조정한다. 이 옵션을 통해 애플리케이션이 폴링 루프에서 데이터를 얼마나 가져올지 양을 조정할 수 있다.
    @Value("${KAFKA_SERVICE_POLL_RECORDS}") String POLL_RECORDS;
    //한번에 가져올 수 있는 최소 데이터 사이즈이다. 만약 지정한 사이즈보다 작은 경우, 요청에 대해 응답하지 않고 데이터가 누적될 때까지 기다린다.
    @Value("${KAFKA_SERVICE_FETCH_MIN_BYTES}") String FETCH_MIN_BYTES;
    //@Value("${KAFKA_SERVICE_Concurrency}") int Concurrency;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        return new DefaultKafkaConsumerFactory<>(config);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


    @Bean
    public ConsumerFactory<String, CookieVo> userConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.251.0.222:9092,10.251.0.223:9092,10.251.0.224:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group-mobon-cookiestore");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(CookieVo.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CookieVo> userKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CookieVo> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userConsumerFactory());
        return factory;
    }

}