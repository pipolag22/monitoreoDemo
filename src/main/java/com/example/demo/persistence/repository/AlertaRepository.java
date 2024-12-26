package com.example.demo.persistence.repository;
import com.example.demo.persistence.entities.Alerta;
import com.example.demo.persistence.entities.AlertaNivel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findByNivel(AlertaNivel nivel);
}