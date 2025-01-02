package com.example.demo.services;

import com.example.demo.persistence.Dto.PlantaRequestDto;
import com.example.demo.persistence.Dto.PlantaResponseDto;
import com.example.demo.persistence.entities.ApiResponse;
import com.example.demo.persistence.entities.Planta;
import com.example.demo.persistence.repository.PlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantaService {

    @Autowired
    private PlantaRepository plantaRepository;

    // Obtener
    public List<Planta> getAllPlantas() {
        return plantaRepository.findAll();
    }

    // Crear
    public Planta createPlanta(Planta planta) {
        return plantaRepository.save(planta);
    }

    // Actualizar
    public Optional<Planta> updatePlanta(Long id, PlantaRequestDto plantaDto) {
        return plantaRepository.findById(id).map(planta -> {
            planta.setName(plantaDto.getName());
            planta.setPais(plantaDto.getPais());
            planta.setLecturas(plantaDto.getLecturas());
            return plantaRepository.save(planta);
        });
    }

    // Eliminar
    public boolean deletePlanta(Long id) {
        return plantaRepository.findById(id).map(planta -> {
            plantaRepository.delete(planta);
            return true;
        }).orElse(false);
    }
}
