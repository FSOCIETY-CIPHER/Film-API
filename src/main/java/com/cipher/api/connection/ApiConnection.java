package com.cipher.api.connection;

import lombok.NoArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
@Component
@NoArgsConstructor
public class ApiConnection {

    public <R> R connectAndGet(String url,Class<R> responseBody){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");
        headers.set("Vary","Accept");
        headers.set("Allow","GET");

        HttpEntity<Object> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(40))
                .setReadTimeout(Duration.ofSeconds(40))
                .build();
        ResponseEntity<R> response = restTemplate.exchange(url, HttpMethod.GET
        ,entity,responseBody);
        return response.getBody();
    }
}
