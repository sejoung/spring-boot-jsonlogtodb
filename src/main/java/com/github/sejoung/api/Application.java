package com.github.sejoung.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author kim se joung
 *
 */
@SpringBootApplication
@EnableAsync
public class Application {

    public static void main(String[] args) throws Exception {
        
        SpringApplication.run(Application.class, args);
    }

}
