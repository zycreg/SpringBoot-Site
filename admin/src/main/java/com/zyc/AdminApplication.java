package com.zyc;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by zhangyuancheng on 04/09/2017.
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
public class AdminApplication {
    private static Logger logger = Logger.getLogger(AdminApplication.class);

    /**
     * Start
     */
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        logger.info("SpringBoot Start Success");
    }

}