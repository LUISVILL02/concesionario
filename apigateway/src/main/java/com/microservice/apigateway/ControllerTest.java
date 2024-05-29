package com.microservice.apigateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControllerTest {
    @RequestMapping("/test")
    public String test() {
        return "Hello World!";
    }
}
