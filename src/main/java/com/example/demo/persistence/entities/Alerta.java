package com.example.demo.persistence.entities;
import jakarta.persistence.*;

@Entity
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private AlertaNivel nivel;

    @ManyToOne
    private Planta planta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public AlertaNivel getNivel() {
        return nivel;
    }

    public void setNivel(AlertaNivel nivel) {
        this.nivel = nivel;
    }

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }
}
