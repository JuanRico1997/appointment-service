package com.vettrack.appointment.application.service;

import com.vettrack.appointment.domain.exception.CitaNotFoundException;
import com.vettrack.appointment.domain.model.Cita;
import com.vettrack.appointment.domain.model.Diagnostico;
import com.vettrack.appointment.domain.ports.in.RegistrarDiagnosticoUseCase;
import com.vettrack.appointment.domain.ports.out.CitaRepositoryPort;
import com.vettrack.appointment.domain.ports.out.DiagnosticoRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrarDiagnosticoService implements RegistrarDiagnosticoUseCase {

    private final CitaRepositoryPort citaRepositoryPort;
    private final DiagnosticoRepositoryPort diagnosticoRepositoryPort;

    public RegistrarDiagnosticoService(CitaRepositoryPort citaRepositoryPort,
                                       DiagnosticoRepositoryPort diagnosticoRepositoryPort) {
        this.citaRepositoryPort = citaRepositoryPort;
        this.diagnosticoRepositoryPort = diagnosticoRepositoryPort;
    }

    @Override
    public Cita registrarDiagnostico(Long citaId, Diagnostico diagnostico) {

        // 1. Buscar la cita
        Cita cita = citaRepositoryPort.findById(citaId)
                .orElseThrow(() -> new CitaNotFoundException(citaId));

        // 2. Guardar el diagnóstico primero
        Diagnostico diagnosticoGuardado = diagnosticoRepositoryPort.save(diagnostico);

        // 3. Registrar el diagnóstico en la cita (la lógica está en el dominio)
        cita.registrarDiagnostico(diagnosticoGuardado);

        // 4. Guardar la cita actualizada y retornar
        return citaRepositoryPort.save(cita);
    }
}