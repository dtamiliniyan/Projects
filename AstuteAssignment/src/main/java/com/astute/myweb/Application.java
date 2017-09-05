package com.astute.myweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Tamiliniyan
 *
 * Spring boot base program to run this application.
 * This program will bring embedded tomcat server up and deploy application.
 * Also inject all the Objects including REST and MVC controllers into container.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
