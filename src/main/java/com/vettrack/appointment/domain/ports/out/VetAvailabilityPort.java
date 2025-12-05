package com.vettrack.appointment.domain.ports.out;

import java.time.LocalDate;
import java.time.LocalTime;

public interface VetAvailabilityPort {

    /**
     * Verifica si un veterinario está disponible en una fecha y hora específica
     *
     * @param veterinarioId ID del veterinario
     * @param fecha Fecha de la cita
     * @param hora Hora de la cita
     * @return true si está disponible, false si no
     */
    AvailabilityResponse checkAvailability(Long veterinarioId, LocalDate fecha, LocalTime hora);

    /**
     * Clase interna para la respuesta de disponibilidad
     */
    class AvailabilityResponse {
        private final Long veterinarioId;
        private final Boolean disponible;
        private final String motivo;

        public AvailabilityResponse(Long veterinarioId, Boolean disponible, String motivo) {
            this.veterinarioId = veterinarioId;
            this.disponible = disponible;
            this.motivo = motivo;
        }

        public Long getVeterinarioId() {
            return veterinarioId;
        }

        public Boolean getDisponible() {
            return disponible;
        }

        public String getMotivo() {
            return motivo;
        }
    }
}