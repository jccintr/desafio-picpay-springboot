package com.jcsoftware.desafio_picpay.services;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcsoftware.desafio_picpay.entities.dtos.AuthorizationResponseDTO;

@Service
public class AuthorizationService {

    private static final String AUTH_URL = "https://util.devi.tools/api/v2/authorize";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public AuthorizationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public boolean isAuthorized() {
    	
        try {
            ResponseEntity<AuthorizationResponseDTO> response = restTemplate.exchange(
                AUTH_URL,
                HttpMethod.GET,
                null,
                AuthorizationResponseDTO.class
            );

            return "success".equalsIgnoreCase(response.getBody().status());
                

        } catch (HttpClientErrorException.Forbidden ex) {
            try {
                String json = ex.getResponseBodyAsString();
                JsonNode root = objectMapper.readTree(json);
                return root.path("data").path("authorization").asBoolean(false); 
            } catch (Exception e) {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }
}
