package com.example.demo.persistence.repository;
import com.example.demo.persistence.entities.Planta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantaRepository extends JpaRepository<Planta, Long> {
}