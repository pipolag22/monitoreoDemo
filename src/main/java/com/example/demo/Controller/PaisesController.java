package com.example.demo.Controller;

import com.example.demo.services.PaisesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/paises")
public class PaisesController {

    @Autowired
    private PaisesServices paisesServices;

    @GetMapping
    public ResponseEntity<?> getAllCountries() {
        try {
            List<Map<String, Object>> countries = paisesServices.getCountries();
            if (countries.isEmpty()) {
                return ResponseEntity.status(204).body("No se encontraron países.");
            }
            return ResponseEntity.ok(countries);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener los países: " + e.getMessage());
        }
    }
}

