package io.pioneerlabs.toggles.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.pioneerlabs.toggles.springboot.starter.EnableToggles;


@SpringBootApplication
@EnableToggles
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class,
                args);
    }


}