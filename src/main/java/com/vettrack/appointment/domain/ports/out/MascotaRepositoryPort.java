package com.vettrack.appointment.domain.ports.out;

import com.vettrack.appointment.domain.model.Mascota;

import java.util.List;
import java.util.Optional;

public interface MascotaRepositoryPort {

    Mascota save(Mascota mascota);

    Optional<Mascota> findById(Long id);

    List<Mascota> findAll();

    List<Mascota> findByDocumentoDueno(String documentoDueno);

    void deleteById(Long id);

    boolean existsById(Long id);
}