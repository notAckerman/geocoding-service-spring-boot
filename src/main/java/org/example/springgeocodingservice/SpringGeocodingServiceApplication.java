package org.example.springgeocodingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringGeocodingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGeocodingServiceApplication.class, args);
    }

}
