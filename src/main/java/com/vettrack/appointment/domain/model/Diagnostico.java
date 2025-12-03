package com.vettrack.appointment.domain.model;

import com.vettrack.appointment.domain.exception.DomainException;

public class Diagnostico {

    private Long id;
    private String descripcion;
    private String tratamientoSugerido;
    private String recomendaciones;

    // Constructor vacío
    public Diagnostico() {
    }

    // Constructor completo
    public Diagnostico(Long id, String descripcion,
                       String tratamientoSugerido, String recomendaciones) {
        this.id = id;
        this.descripcion = descripcion;
        this.tratamientoSugerido = tratamientoSugerido;
        this.recomendaciones = recomendaciones;
    }

    // ✅ LÓGICA DE NEGOCIO

    public void validarDatos() {
        if (descripcion == null || descripcion.isBlank()) {
            throw new DomainException("La descripcion del diagnostico es obligatoria");
        }

        if (tratamientoSugerido == null || tratamientoSugerido.isBlank()) {
            throw new DomainException("El tratamiento sugerido es obligatorio");
        }
    }

    public boolean esCompleto() {
        return descripcion != null && !descripcion.isBlank() &&
                tratamientoSugerido != null && !tratamientoSugerido.isBlank() &&
                recomendaciones != null && !recomendaciones.isBlank();
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
}