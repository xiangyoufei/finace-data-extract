package com.lysss.finance.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Configurable
@Component
public class RestTemplateConfig {

    @Bean
    public RestTemplate buildTemplate() {
        return new RestTemplate();
    }

}
