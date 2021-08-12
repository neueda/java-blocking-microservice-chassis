package com.neueda.blocking.chassis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ConfigurationPropertiesScan
public class JavaBlockingMicroserviceChassisApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaBlockingMicroserviceChassisApplication.class, args);
    }

}