package com.lilesien.comsume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class ComsumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComsumeApplication.class, args);
    }


}
