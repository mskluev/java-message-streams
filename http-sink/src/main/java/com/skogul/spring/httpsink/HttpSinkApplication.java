package com.skogul.spring.httpsink;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HttpSinkApplication {

    private static final Logger log = LoggerFactory.getLogger(HttpSinkApplication.class);

    @Autowired
    private ObjectMapper jsonMapper;

    @Autowired
    private StreamBridge streamBridge;

    public static void main(String[] args) {
        SpringApplication.run(HttpSinkApplication.class, "--spring.cloud.stream.source=http-sink");
    }

    @SuppressWarnings("unchecked")
    @PostMapping(path = "/", consumes = "*/*")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void handleRequest(@RequestBody String body, @RequestHeader(HttpHeaders.CONTENT_TYPE) Object contentType)
            throws Exception {
        Map<String, String> payload = jsonMapper.readValue(body, Map.class);
        Message<?> message = MessageBuilder.withPayload(payload).build();
        log.info("Data received: " + payload);
        streamBridge.send("http-sink-out-0", message);
    }

}
