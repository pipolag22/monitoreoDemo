package com.example.demo.Controller;

import com.example.demo.persistence.entities.Planta;
import com.example.demo.services.PlantaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plantas")
public class PlantaController {

    @Autowired
    private PlantaService plantaService;

    // todas las plantas
    @GetMapping
    public List<Planta> getAllPlantas(){
        return plantaService.getAllPlantas();
    }
    // Ccear nueva planta
    @PostMapping
    public Planta createPlanta(@RequestBody Planta planta) {
        return plantaService.createPlanta(planta);
    }

    // editar planta
    @PutMapping("/{id}")
    public Planta updatePlanta(@PathVariable Long id, @RequestBody Planta plantaDetails) {
        return plantaService.updatePlanta(id, plantaDetails).orElseThrow(() -> new RuntimeException("Planta no encontrada"));
    }

    // eliminar planta
    @DeleteMapping("/{id}")
    public void deletePlanta(@PathVariable Long id) {
        if (!plantaService.deletePlanta(id)) {
            throw new RuntimeException("Planta no encontrada");
        }
    }
}
