package com.vettrack.appointment.infrastructure.adapter.out.persistence.adapter;

import com.vettrack.appointment.domain.model.Mascota;
import com.vettrack.appointment.domain.ports.out.MascotaRepositoryPort;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.entity.MascotaEntity;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.mapper.MascotaMapper;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.repository.MascotaJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MascotaRepositoryAdapter implements MascotaRepositoryPort {

    private final MascotaJpaRepository mascotaJpaRepository;
    private final MascotaMapper mascotaMapper;

    public MascotaRepositoryAdapter(MascotaJpaRepository mascotaJpaRepository,
                                    MascotaMapper mascotaMapper) {
        this.mascotaJpaRepository = mascotaJpaRepository;
        this.mascotaMapper = mascotaMapper;
    }

    @Override
    public Mascota save(Mascota mascota) {
        MascotaEntity entity = mascotaMapper.toEntity(mascota);
        MascotaEntity savedEntity = mascotaJpaRepository.save(entity);
        return mascotaMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Mascota> findById(Long id) {
        return mascotaJpaRepository.findById(id)
                .map(mascotaMapper::toDomain);
    }

    @Override
    public List<Mascota> findAll() {
        return mascotaJpaRepository.findAll().stream()
                .map(mascotaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Mascota> findByDocumentoDueno(String documentoDueno) {
        return mascotaJpaRepository.findByDocumentoDueno(documentoDueno).stream()
                .map(mascotaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        mascotaJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return mascotaJpaRepository.existsById(id);
    }
}