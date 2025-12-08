package com.vettrack.appointment.infrastructure.adapter.out.persistence.adapter;

import com.vettrack.appointment.domain.model.Veterinario;
import com.vettrack.appointment.domain.ports.out.VeterinarioRepositoryPort;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.entity.VeterinarioEntity;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.mapper.VeterinarioMapper;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.repository.VeterinarioJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VeterinarioRepositoryAdapter implements VeterinarioRepositoryPort {

    private final VeterinarioJpaRepository veterinarioJpaRepository;
    private final VeterinarioMapper veterinarioMapper;

    public VeterinarioRepositoryAdapter(VeterinarioJpaRepository veterinarioJpaRepository,
                                        VeterinarioMapper veterinarioMapper) {
        this.veterinarioJpaRepository = veterinarioJpaRepository;
        this.veterinarioMapper = veterinarioMapper;
    }

    @Override
    public Veterinario save(Veterinario veterinario) {
        VeterinarioEntity entity = veterinarioMapper.toEntity(veterinario);
        VeterinarioEntity savedEntity = veterinarioJpaRepository.save(entity);
        return veterinarioMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Veterinario> findById(Long id) {
        return veterinarioJpaRepository.findById(id)
                .map(veterinarioMapper::toDomain);
    }

    @Override
    public List<Veterinario> findAll() {
        return veterinarioJpaRepository.findAll().stream()
                .map(veterinarioMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Veterinario> findByActivo(Boolean activo) {
        return veterinarioJpaRepository.findByActivo(activo).stream()
                .map(veterinarioMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        veterinarioJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return veterinarioJpaRepository.existsById(id);
    }
}