package net.banck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final GreetingClient greetingClient;

    public GreetingController(GreetingClient greetingClient) {
        this.greetingClient = greetingClient;
    }

    @GetMapping("/get-greeting")
    public String greeting() {

        return greetingClient.greeting();
    }
}
