package com.vettrack.appointment.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "diagnosticos")
public class DiagnosticoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "tratamiento_sugerido", nullable = false, columnDefinition = "TEXT")
    private String tratamientoSugerido;

    @Column(columnDefinition = "TEXT")
    private String recomendaciones;

    // Relación 1-1 con Cita (lado inverso)
    @OneToOne(mappedBy = "diagnostico")
    private CitaEntity cita;

    // Constructor vacío
    public DiagnosticoEntity() {
    }

    // Constructor completo
    public DiagnosticoEntity(Long id, String descripcion,
                             String tratamientoSugerido, String recomendaciones) {
        this.id = id;
        this.descripcion = descripcion;
        this.tratamientoSugerido = tratamientoSugerido;
        this.recomendaciones = recomendaciones;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTratamientoSugerido() {
        return tratamientoSugerido;
    }

    public void setTratamientoSugerido(String tratamientoSugerido) {
        this.tratamientoSugerido = tratamientoSugerido;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public CitaEntity getCita() {
        return cita;
    }

    public void setCita(CitaEntity cita) {
        this.cita = cita;
    }
}