package com.vettrack.appointment.domain.ports.out;

import com.vettrack.appointment.domain.model.Diagnostico;

import java.util.Optional;

public interface DiagnosticoRepositoryPort {

    Diagnostico save(Diagnostico diagnostico);

    Optional<Diagnostico> findById(Long id);

    void deleteById(Long id);
}