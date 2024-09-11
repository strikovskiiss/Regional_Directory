package com.example.regional_directory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RegionalDirectoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegionalDirectoryApplication.class, args);
    }

}
