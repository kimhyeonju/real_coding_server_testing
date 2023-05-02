package com.cnu.real_coding_server.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RabbitTestConsumer {
    private static int count = 10;
    @Autowired
    ObjectMapper mapper;

    @RabbitListener(queues = "inspire12_test", concurrency = "10", ackMode = "AUTO")
    void test(org.springframework.messaging.Message<byte[]> message
            , Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag
    )
            throws InterruptedException, IOException {
        log.info("{} {} {}", message, channel, tag);
        TypeReference<HashMap<String, Object>> typeRef
                = new TypeReference<>() {
        };
        Map<String, Object> dataMap = mapper.readValue(message.getPayload(), typeRef);

        log.info("inspire12_test queue: {} {}", count, message);
        count--;
        if (count == 5) {
            tag = 1L;
        }

    }
}
