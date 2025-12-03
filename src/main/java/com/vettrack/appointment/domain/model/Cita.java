package com.vettrack.appointment.domain.model;

import com.vettrack.appointment.domain.enums.EstadoCita;
import com.vettrack.appointment.domain.exception.CitaYaConfirmadaException;
import com.vettrack.appointment.domain.exception.DomainException;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {

    private Long id;
    private Mascota mascota;
    private Veterinario veterinario;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private EstadoCita estado;
    private Diagnostico diagnostico;

    // Constructor vacío
    public Cita() {
        this.estado = EstadoCita.PENDIENTE; // Por defecto PENDIENTE
    }

    // Constructor completo
    public Cita(Long id, Mascota mascota, Veterinario veterinario,
                LocalDate fecha, LocalTime hora, String motivo,
                EstadoCita estado, Diagnostico diagnostico) {
        this.id = id;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.estado = estado != null ? estado : EstadoCita.PENDIENTE;
        this.diagnostico = diagnostico;
    }

    // ✅ LÓGICA DE NEGOCIO - GESTIÓN DE ESTADOS

    public void confirmar() {
        if (this.estado != EstadoCita.PENDIENTE) {
            throw new CitaYaConfirmadaException(
                    "Solo se pueden confirmar citas en estado PENDIENTE"
            );
        }
        this.estado = EstadoCita.CONFIRMADA;
    }

    public void cancelar() {
        if (this.estado == EstadoCita.CANCELADA) {
            throw new DomainException("La cita ya esta cancelada");
        }
        this.estado = EstadoCita.CANCELADA;
    }

    public void registrarDiagnostico(Diagnostico diagnostico) {
        if (this.estado != EstadoCita.CONFIRMADA) {
            throw new DomainException(
                    "Solo se puede registrar diagnostico en citas CONFIRMADAS"
            );
        }

        if (diagnostico == null) {
            throw new DomainException("El diagnostico no puede ser nulo");
        }

        diagnostico.validarDatos();
        this.diagnostico = diagnostico;
    }

    // ✅ VALIDACIONES

    public void validarDatos() {
        if (mascota == null) {
            throw new DomainException("La mascota es obligatoria");
        }

        if (veterinario == null) {
            throw new DomainException("El veterinario es obligatorio");
        }

        if (fecha == null) {
            throw new DomainException("La fecha es obligatoria");
        }

        if (hora == null) {
            throw new DomainException("La hora es obligatoria");
        }

        if (motivo == null || motivo.isBlank()) {
            throw new DomainException("El motivo de la cita es obligatorio");
        }
    }

    // ✅ CONSULTAS DE ESTADO

    public boolean estaPendiente() {
        return this.estado == EstadoCita.PENDIENTE;
    }

    public boolean estaConfirmada() {
        return this.estado == EstadoCita.CONFIRMADA;
    }

    public boolean estaCancelada() {
        return this.estado == EstadoCita.CANCELADA;
    }

    public boolean tieneDiagnostico() {
        return this.diagnostico != null;
    }

    // ✅ CONTROL DE ACCESO (para seguridad)

    public boolean perteneceADueno(String documentoDueno) {
        return this.mascota != null &&
                this.mascota.getDocumentoDueno().equals(documentoDueno);
    }

    public boolean perteneceAVeterinario(Long veterinarioId) {
        return this.veterinario != null &&
                this.veterinario.getId().equals(veterinarioId);
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
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

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }
}
