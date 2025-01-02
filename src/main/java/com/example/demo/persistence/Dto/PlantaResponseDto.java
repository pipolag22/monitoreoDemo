package com.example.demo.persistence.Dto;


public class PlantaResponseDto {
    private Long id;
    private String name;
    private String pais;
    private Integer lecturas;

    // Getters y Setters
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
}