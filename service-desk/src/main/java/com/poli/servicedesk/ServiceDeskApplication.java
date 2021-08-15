package com.poli.servicedesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.poli.servicedesk.*"})
@EntityScan("com.poli.servicedesk.model")
@EnableJpaRepositories("com.poli.servicedesk.repository.*")
public class ServiceDeskApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDeskApplication.class, args);
    }

}
