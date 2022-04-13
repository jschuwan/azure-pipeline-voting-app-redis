package com.example.reimbapi1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api1")
public class Api1Controller {

//    @Value("${api.config.api2-url:http://localhost:7072/api2}")       // for when we run without containers
    @Value("http://api2:7072/api2")                                     // for when we run in docker compose
    String api2Url;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<?> createNewResource(@RequestBody  String message) {
        System.out.println("API1>> we received a message.. \n\t\t " + message);
        // send our message to api2

        // this is synchronous
        // it will block until the other api returns
        ResponseEntity api1Response = restTemplate.postForEntity(api2Url, message, null);

        if(api1Response.getStatusCode().is5xxServerError()) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.noContent().build();
    }
}

/*    @PostMapping
    public ResponseEntity<?> createNewResource(@RequestBody String message) {
        return ResponseEntity.ok().build();
    }*/
