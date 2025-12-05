package com.vettrack.appointment.domain.service;

import com.vettrack.appointment.domain.exception.HorarioInvalidoException;
import com.vettrack.appointment.domain.exception.MascotaNoActivaException;
import com.vettrack.appointment.domain.exception.VeterinarioNoDisponibleException;
import com.vettrack.appointment.domain.model.Mascota;
import com.vettrack.appointment.domain.model.Veterinario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CitaDomainService {

    /**
     * Valida que el horario de la cita sea válido
     * - No puede ser en el pasado
     * - Debe tener al menos 2 horas de anticipación
     */
    public void validarHorarioCita(LocalDate fecha, LocalTime hora) {

        if (fecha == null || hora == null) {
            throw new HorarioInvalidoException("Fecha y hora son obligatorias");
        }

        LocalDateTime citaDateTime = LocalDateTime.of(fecha, hora);
        LocalDateTime ahora = LocalDateTime.now();

        // Validar que no sea en el pasado
        if (citaDateTime.isBefore(ahora)) {
            throw new HorarioInvalidoException(
                    "No se pueden agendar citas en el pasado"
            );
        }

        // Validar que tenga al menos 2 horas de anticipación
        LocalDateTime minimoPermitido = ahora.plusHours(2);
        if (citaDateTime.isBefore(minimoPermitido)) {
            throw new HorarioInvalidoException(
                    "Las citas deben agendarse con al menos 2 horas de anticipacion"
            );
        }
    }

    /**
     * Valida que la mascota esté activa y pueda reservar citas
     */
    public void validarMascotaActiva(Mascota mascota) {

        if (mascota == null) {
            throw new MascotaNoActivaException("La mascota no puede ser nula");
        }

        if (!mascota.puedeReservarCita()) {
            throw new MascotaNoActivaException(
                    "Solo mascotas ACTIVAS pueden reservar citas"
            );
        }
    }

    /**
     * Valida que el veterinario esté activo
     */
    public void validarVeterinarioActivo(Veterinario veterinario) {

        if (veterinario == null) {
            throw new VeterinarioNoDisponibleException("El veterinario no puede ser nulo");
        }

        if (!veterinario.estaActivo()) {
            throw new VeterinarioNoDisponibleException(
                    "El veterinario no esta activo"
            );
        }
    }

    /**
     * Valida la respuesta del servicio de disponibilidad
     */
    public void validarDisponibilidadVeterinario(boolean disponible, String motivo) {

        if (!disponible) {
            throw new VeterinarioNoDisponibleException(
                    "El veterinario no esta disponible: " + motivo
            );
        }
    }

    /**
     * Valida todas las reglas de negocio para solicitar una cita
     */
    public void validarSolicitudCita(Mascota mascota, Veterinario veterinario,
                                     LocalDate fecha, LocalTime hora) {

        validarMascotaActiva(mascota);
        validarVeterinarioActivo(veterinario);
        validarHorarioCita(fecha, hora);
    }
}