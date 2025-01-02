package com.example.demo.Controller;

import com.example.demo.persistence.entities.Alerta;
import com.example.demo.persistence.entities.AlertaNivel;
import com.example.demo.services.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;


    @GetMapping
    public List<Alerta> getAllAlertas(){
        return alertaService.getAllAlertas();
    }


    @GetMapping("/{nivel}")
    public List<Alerta> getAlertasByNivel(@PathVariable AlertaNivel nivel){
        return alertaService.getAlertasByNivel(nivel);
    }
    @GetMapping("/deshabilitados")
    public List<Alerta> getSensoresDeshabilitados() {
        return alertaService.getAlertasByNivel(AlertaNivel.DESHABILITADO);
    }


}
