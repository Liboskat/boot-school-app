package ru.kpfu.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.kpfu.controllers", "ru.kpfu.security", "ru.kpfu.services", "ru.kpfu.validators"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackages = "ru.kpfu.repositories")
@EntityScan(basePackages = "ru.kpfu.models")
public class SchoolApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);
    }
}
