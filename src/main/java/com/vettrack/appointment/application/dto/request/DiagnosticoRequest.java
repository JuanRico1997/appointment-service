package com.vettrack.appointment.application.dto.request;

public class DiagnosticoRequest {

    private String descripcion;
    private String tratamientoSugerido;
    private String recomendaciones;

    // Constructor vacío
    public DiagnosticoRequest() {
    }

    // Constructor completo
    public DiagnosticoRequest(String descripcion, String tratamientoSugerido,
                              String recomendaciones) {
        this.descripcion = descripcion;
        this.tratamientoSugerido = tratamientoSugerido;
        this.recomendaciones = recomendaciones;
    }

    // Getters y Setters

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