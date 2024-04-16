package com.cristian.project.api.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GsonConfig {

    @Bean
    public Gson getGeson() {
        return new Gson();
    }

}
