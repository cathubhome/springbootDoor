package com.ciwr.controller.kafka;

import com.ciwr.global.common.utils.G;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * Author:catHome
 * Description: kafaka生产者
 * Time:Create on 2018/9/12 22:04
 */
@RestController
@Slf4j
@RequestMapping("kafka")
public class Producer {

    @Value("${kafka.test.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @ApiOperation("测试卡夫卡生产者")
    @RequestMapping(value = "/send", method = RequestMethod.GET)
        public G sendKafka(@RequestParam(required = true) String key, @RequestParam(required = true) String message) {
        try {
            kafkaTemplate.send(topic,key,message);
            log.debug("kafka send message topic-{},key-{},message-{}",topic,key,message);
            return G.ok();
        } catch (Exception e) {
            log.error("kafka send message topic-{},key-{},message-{} error",topic,key,message);
            return G.error(e.getMessage());
        }
    }
}
