package net.banck.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "resource", configuration = FeignConfiguration.class)
public interface GreetingClient {
    @RequestMapping("/greeting")
    String greeting();
}


