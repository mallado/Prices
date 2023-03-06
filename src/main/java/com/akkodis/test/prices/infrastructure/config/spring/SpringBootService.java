package com.akkodis.test.prices.infrastructure.config.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.akkodis.test.prices")
@EntityScan(basePackages = "com.akkodis.test.prices")
public class SpringBootService {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootService.class, args);
  }

}