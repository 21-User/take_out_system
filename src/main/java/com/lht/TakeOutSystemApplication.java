package com.lht;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 21
 */
@Slf4j
@SpringBootApplication
public class TakeOutSystemApplication {


    public static void main(String[] args) {
        SpringApplication.run(TakeOutSystemApplication.class, args);

        log.info("==============项目启动成功============== ");
    }

}
