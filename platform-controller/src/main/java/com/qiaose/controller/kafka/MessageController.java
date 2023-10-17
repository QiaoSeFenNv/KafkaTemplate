package com.qiaose.controller.kafka;




import com.qiaose.kafka.KafkaProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
@Api(tags = "kafka")
public class MessageController {

    private KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/publish")
    @ApiOperation("hello kafka")
    public ResponseEntity<String> publish(@RequestParam("message") String message){
        kafkaProducer.sendMessage("Hello World");
        return ResponseEntity.ok("Message Published");
    }

}
