package com.vettrack.appointment.application.service;

import com.vettrack.appointment.domain.exception.CitaNotFoundException;
import com.vettrack.appointment.domain.model.Cita;
import com.vettrack.appointment.domain.ports.in.CancelarCitaUseCase;
import com.vettrack.appointment.domain.ports.out.CitaRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CancelarCitaService implements CancelarCitaUseCase {

    private final CitaRepositoryPort citaRepositoryPort;

    public CancelarCitaService(CitaRepositoryPort citaRepositoryPort) {
        this.citaRepositoryPort = citaRepositoryPort;
    }

    @Override
    public Cita cancelar(Long citaId) {

        // 1. Buscar la cita
        Cita cita = citaRepositoryPort.findById(citaId)
                .orElseThrow(() -> new CitaNotFoundException(citaId));

        // 2. Cancelar (la lógica está en el dominio)
        cita.cancelar();

        // 3. Guardar y retornar
        return citaRepositoryPort.save(cita);
    }
}