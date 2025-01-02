package com.example.demo.persistence.entities;
import jakarta.persistence.*;


import java.util.List;

@Entity
public class Planta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String pais;
    private Integer lecturas;

    @OneToMany(mappedBy = "planta")
    private List<Alerta> alertas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getLecturas() {
        return lecturas;
    }

    public void setLecturas(Integer lecturas) {
        this.lecturas = lecturas;
    }

    public List<Alerta> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<Alerta> alertas) {
        this.alertas = alertas;
    }
}
