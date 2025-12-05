package com.vettrack.appointment.application.dto.response;

import com.vettrack.appointment.domain.enums.EstadoCita;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaResponse {

    private Long id;
    private MascotaResponse mascota;
    private VeterinarioResponse veterinario;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private EstadoCita estado;
    private DiagnosticoResponse diagnostico;

    // Constructor vacío
    public CitaResponse() {
    }

    // Constructor completo
    public CitaResponse(Long id, MascotaResponse mascota, VeterinarioResponse veterinario,
                        LocalDate fecha, LocalTime hora, String motivo,
                        EstadoCita estado, DiagnosticoResponse diagnostico) {
        this.id = id;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.estado = estado;
        this.diagnostico = diagnostico;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MascotaResponse getMascota() {
        return mascota;
    }

    public void setMascota(MascotaResponse mascota) {
        this.mascota = mascota;
    }

    public VeterinarioResponse getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(VeterinarioResponse veterinario) {
        this.veterinario = veterinario;
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

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }

    public DiagnosticoResponse getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(DiagnosticoResponse diagnostico) {
        this.diagnostico = diagnostico;
    }
}