package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
@Service
public class PaisesServices {
    private static final String API_URL = "https://api.countrylayer.com/v2/all?access_key=5e26379a969e9b568d627f56d4830b07";

    public List<Map<String, Object>> getCountries() {
        RestTemplate restTemplate = new RestTemplate();
        List<Map<String, Object>> countries = restTemplate.getForObject(API_URL, List.class);
        return countries;
    }
}

