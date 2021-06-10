package com.example.rediscache.controller;

import com.example.rediscache.impl.CacheEngineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/redis")
public class Controller {
    private CacheEngineServiceImpl cacheEngineService;

    @Autowired
    public Controller(CacheEngineServiceImpl cacheEngineService) {
        this.cacheEngineService = cacheEngineService;
    }

    @GetMapping(path = "/register")
    public ResponseEntity<String> getRegister(@RequestParam("id") String id) {
        String response;
        response = cacheEngineService.getRegister(id).isPresent() ? cacheEngineService.getRegister(id).get() : "No values";

        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/populate")
    public ResponseEntity<String> populate(){
        for(int i = 0; i < 11; i++)
            cacheEngineService.setRegister(String.valueOf(i),"Register is "+i);

        return ResponseEntity.ok("ok");
    }
}
