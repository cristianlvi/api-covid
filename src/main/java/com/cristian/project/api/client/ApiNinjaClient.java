package com.cristian.project.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "api-ninja", url = "https://api.api-ninjas.com/v1/covid19")
public interface ApiNinjaClient {

    @GetMapping("?country={name}")
    String searchNameCountry(@RequestParam(name = "name") String name, @RequestHeader("x-api-key") String token);

    @GetMapping("?type=deaths&country={name}")
    String searchCasesDeathsCountry(@RequestParam(name = "name") String name, @RequestHeader("x-api-key") String token);

// type = cases ou deaths
}
