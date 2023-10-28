package com.qiaose;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Array;
import java.util.*;

/**
 * @ClassName: ApplicationRun
 * @Description:
 * @Author qiaosefennv
 * @Date 2022/6/11
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = "com.qiaose.*")
@MapperScan("com.qiaose.*")
@EnableOpenApi
@Slf4j
public class ApplicationRun extends SpringBootServletInitializer {
    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(ApplicationRun.class, args);
        Environment env = application.getEnvironment();
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            //
            throw new RuntimeException(e);
        }
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path","");

        log.info("\n----------------------------------------------------------\n\t" +
                "Application  is running! Access URLs:\n\t" +
                "Local访问网址: \t\thttp://localhost:" + port + path + "\n\t" +
                "External访问网址: \thttp://" + ip + ":" + port + path + "\n\t" +
                "Local Swagger2访问网址: \t\thttp://localhost:" + port + path+ "/swagger-ui/index.html " + "\n\t" +
                "External访问网址: \thttp://" + ip + ":" + port + path + "/swagger-ui/index.html" +"\n\t" +
                "----------------------------------------------------------");
    }
}
