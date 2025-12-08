package com.vettrack.appointment.infrastructure.adapter.out.persistence.adapter;

import com.vettrack.appointment.domain.model.Cita;
import com.vettrack.appointment.domain.ports.out.CitaRepositoryPort;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.entity.CitaEntity;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.mapper.CitaMapper;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.repository.CitaJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CitaRepositoryAdapter implements CitaRepositoryPort {

    private final CitaJpaRepository citaJpaRepository;
    private final CitaMapper citaMapper;

    public CitaRepositoryAdapter(CitaJpaRepository citaJpaRepository,
                                 CitaMapper citaMapper) {
        this.citaJpaRepository = citaJpaRepository;
        this.citaMapper = citaMapper;
    }

    @Override
    public Cita save(Cita cita) {
        CitaEntity entity = citaMapper.toEntity(cita);
        CitaEntity savedEntity = citaJpaRepository.save(entity);
        return citaMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Cita> findById(Long id) {
        return citaJpaRepository.findById(id)
                .map(citaMapper::toDomain);
    }

    @Override
    public List<Cita> findAll() {
        return citaJpaRepository.findAll().stream()
                .map(citaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cita> findByMascotaId(Long mascotaId) {
        return citaJpaRepository.findByMascotaId(mascotaId).stream()
                .map(citaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cita> findByVeterinarioId(Long veterinarioId) {
        return citaJpaRepository.findByVeterinarioId(veterinarioId).stream()
                .map(citaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cita> findByDocumentoDueno(String documentoDueno) {
        return citaJpaRepository.findByDocumentoDueno(documentoDueno).stream()
                .map(citaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        citaJpaRepository.deleteById(id);
    }
}
