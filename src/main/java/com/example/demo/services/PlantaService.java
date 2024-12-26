package com.example.demo.services;

import com.example.demo.persistence.entities.Planta;
import com.example.demo.persistence.repository.PlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantaService {

    @Autowired
    private PlantaRepository plantaRepository;

    public List<Planta> getAllPlantas(){
        return plantaRepository.findAll();
    }
    public Planta createPlanta(Planta planta){
        return plantaRepository.save(planta);
    }
    public Optional<Planta> updatePlanta(Long id, Planta plantaDetails){
        return plantaRepository.findById(id).map(planta ->{
            planta.setName(plantaDetails.getName());
            planta.setPais(plantaDetails.getPais());
            planta.setLecturas(plantaDetails.getLecturas());
            return plantaRepository.save(planta);
        });
    }
    public boolean deletePlanta(Long id){
        return plantaRepository.findById(id).map(planta -> {
            plantaRepository.delete(planta);
            return true;
        }).orElse(false);
    }
}
