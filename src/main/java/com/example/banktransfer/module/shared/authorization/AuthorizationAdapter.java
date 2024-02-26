package com.example.banktransfer.module.shared.authorization;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Component
@Configuration
public class AuthorizationAdapter implements IAuthorization {

    private final Environment env;

    private final RestTemplate http;

    public AuthorizationAdapter(Environment env) {
        this.env = env;
        this.http = new RestTemplate();
    }

    @Override
    public boolean auth() {
        ResponseEntity<Map> response = http.getForEntity(Objects.requireNonNull(env.getProperty("AUTHORIZATION_ENDPOINT")), Map.class);
        return response.getStatusCode() == HttpStatus.OK;
    }
}
