package com.atwj.rabbitmq;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

/**
 * @author wj
 * @create_time 2023/6/29
 * @description
 */
@SpringBootTest
@Configuration
@Slf4j
@ConfigurationProperties(prefix = "test")
@Data
public class InjectionTest {


    /**
     * @Value不需要提供set方法
     * @ConfigurationProperties必须提供set方法，否则无法注入
     */


//    @Value("${test.name}")
//    private String name;

    private String name;

    @Test
    void test() {
        log.error(name);
    }

}
