package com.vettrack.appointment.domain.exception;

public class CitaNotFoundException extends DomainException {

    public CitaNotFoundException(String message) {
        super(message);
    }

    public CitaNotFoundException(Long citaId) {
        super("Cita no encontrada con ID: " + citaId);
    }
}