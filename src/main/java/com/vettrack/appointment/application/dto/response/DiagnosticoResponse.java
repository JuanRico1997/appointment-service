package com.vettrack.appointment.application.dto.response;

public class DiagnosticoResponse {

    private Long id;
    private String descripcion;
    private String tratamientoSugerido;
    private String recomendaciones;

    // Constructor vacío
    public DiagnosticoResponse() {
    }

    // Constructor completo
    public DiagnosticoResponse(Long id, String descripcion,
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
}