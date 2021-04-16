package com.example.demo.controller;

import com.example.demo.service.NumberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@Log4j2
public class EntryPointController {

    private final NumberService numberService;

    @Autowired
    public EntryPointController(NumberService numberService) {
        this.numberService = numberService;
    }

    @GetMapping(value = "entry-point/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String entryPoint(@PathVariable Long number){
        log.info("entry-point API is invoked");
        return String.format("{\"square\": %s}", numberService.square(number));
    }

    @PutMapping(value = "cache-evict")
    public String cacheEvict(){
        log.info("cache-evict API is invoked");
        numberService.evict();
        return "done";
    }

    @PostMapping(value = "cache-put")
    public String cachePut(){
        log.info("cache-put API is invoked");
        numberService.cachePut(1l);
        return "done1";
    }
}
