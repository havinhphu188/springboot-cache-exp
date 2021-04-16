package com.example.demo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Log4j2
public class NumberService {

    @Cacheable(value = "squareCache")
    public BigDecimal square(Long number) {
        BigDecimal square = BigDecimal.valueOf(number)
                .multiply(BigDecimal.valueOf(number));
        log.info("square of {} is {}", number, square);
        return square;
    }

    @CacheEvict(cacheNames="squareCache", allEntries=true)
    public void evict(){
        log.info("evict service");
    }

    @CachePut(value = "squareCache")
    public BigDecimal cachePut(Long number){
        log.info("cache put service");
        return BigDecimal.valueOf(8);
    }

}