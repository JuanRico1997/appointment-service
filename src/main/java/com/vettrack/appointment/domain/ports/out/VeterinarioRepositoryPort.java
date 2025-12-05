package com.vettrack.appointment.domain.ports.out;

import com.vettrack.appointment.domain.model.Veterinario;

import java.util.List;
import java.util.Optional;

public interface VeterinarioRepositoryPort {

    Veterinario save(Veterinario veterinario);

    Optional<Veterinario> findById(Long id);

    List<Veterinario> findAll();

    List<Veterinario> findByActivo(Boolean activo);

    void deleteById(Long id);

    boolean existsById(Long id);
}