package com.vettrack.appointment.domain.ports.in;

import com.vettrack.appointment.domain.model.Cita;

public interface CancelarCitaUseCase {

    Cita cancelar(Long citaId);
}