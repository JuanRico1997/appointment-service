package com.vettrack.appointment.domain.ports.out;

import com.vettrack.appointment.domain.model.Cita;

import java.util.List;
import java.util.Optional;

public interface CitaRepositoryPort {

    Cita save(Cita cita);

    Optional<Cita> findById(Long id);

    List<Cita> findAll();

    List<Cita> findByMascotaId(Long mascotaId);

    List<Cita> findByVeterinarioId(Long veterinarioId);

    List<Cita> findByDocumentoDueno(String documentoDueno);

    void deleteById(Long id);
}