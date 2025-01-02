package com.example.demo.Controller;

import com.example.demo.persistence.entities.Planta;
import com.example.demo.services.PlantaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //crear planta
    @PostMapping
    public ResponseEntity<?> createPlanta(@RequestBody Planta planta) {
        if (planta.getName() == null || planta.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre de la planta es obligatorio");
        }
        if (planta.getPais() == null || planta.getPais().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El país de la planta es obligatorio");
        }
        return ResponseEntity.ok(plantaService.createPlanta(planta));
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
