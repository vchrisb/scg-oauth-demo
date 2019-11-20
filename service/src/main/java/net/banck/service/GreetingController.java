package net.banck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    private GreetingClient greetingClient;

    @GetMapping("/get-greeting")
    public String greeting() {
        return greetingClient.greeting();
    }
}
