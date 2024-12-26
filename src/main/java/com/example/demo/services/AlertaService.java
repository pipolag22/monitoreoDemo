package com.example.demo.services;

import com.example.demo.persistence.entities.Alerta;
import com.example.demo.persistence.entities.AlertaNivel;
import com.example.demo.persistence.repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    public List<Alerta> getAllAlertas() {
        return alertaRepository.findAll();
    }

    public List<Alerta> getAlertasByNivel(AlertaNivel nivel) {
        return alertaRepository.findByNivel(nivel);
    }

    public Alerta createAlerta(Alerta alerta) {
        return alertaRepository.save(alerta);
    }

    public Optional<Alerta> updateAlerta(Long id, Alerta alertaDetails) {
        return alertaRepository.findById(id).map(alerta -> {
            alerta.setTipo(alertaDetails.getTipo());
            alerta.setDescripcion(alertaDetails.getDescripcion());
            alerta.setNivel(alertaDetails.getNivel());
            return alertaRepository.save(alerta);
        });
    }

    public boolean deleteAlerta(Long id) {
        return alertaRepository.findById(id).map(alerta -> {
            alertaRepository.delete(alerta);
            return true;
        }).orElse(false);
    }
}