package com.akkodis.test.prices.infrastructure.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.akkodis.test.prices.application.PriceService;
import com.akkodis.test.prices.infrastructure.inputadapter.rest.mapper.PriceRestMapper;
import com.akkodis.test.prices.infrastructure.outputport.PriceRepositoryPort;

@Configuration
public class SpringBootServiceConfig {

    @Bean
    PriceService priceService(PriceRepositoryPort priceDboRepository, PriceRestMapper priceRestMapper) {
        return new PriceService(priceDboRepository, priceRestMapper);
    }
}