package com.vettrack.appointment.domain.ports.in;

import com.vettrack.appointment.domain.model.Cita;
import com.vettrack.appointment.domain.model.Diagnostico;

public interface RegistrarDiagnosticoUseCase {

    Cita registrarDiagnostico(Long citaId, Diagnostico diagnostico);
}