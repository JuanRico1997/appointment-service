package com.vettrack.appointment.application.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaRequest {

    private Long mascotaId;
    private Long veterinarioId;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;

    // Constructor vacío
    public CitaRequest() {
    }

    // Constructor completo
    public CitaRequest(Long mascotaId, Long veterinarioId,
                       LocalDate fecha, LocalTime hora, String motivo) {
        this.mascotaId = mascotaId;
        this.veterinarioId = veterinarioId;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
    }

    // Getters y Setters

    public Long getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(Long mascotaId) {
        this.mascotaId = mascotaId;
    }

    public Long getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(Long veterinarioId) {
        this.veterinarioId = veterinarioId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}