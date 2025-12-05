package com.vettrack.appointment.domain.ports.in;

import com.vettrack.appointment.domain.model.Cita;

import java.time.LocalDate;
import java.time.LocalTime;

public interface SolicitarCitaUseCase {

    Cita solicitar(Long mascotaId, Long veterinarioId,
                   LocalDate fecha, LocalTime hora, String motivo);
}