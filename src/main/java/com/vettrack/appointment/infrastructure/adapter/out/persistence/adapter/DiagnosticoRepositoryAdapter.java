package com.vettrack.appointment.infrastructure.adapter.out.persistence.adapter;

import com.vettrack.appointment.domain.model.Diagnostico;
import com.vettrack.appointment.domain.ports.out.DiagnosticoRepositoryPort;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.entity.DiagnosticoEntity;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.mapper.DiagnosticoMapper;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.repository.DiagnosticoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DiagnosticoRepositoryAdapter implements DiagnosticoRepositoryPort {

    private final DiagnosticoJpaRepository diagnosticoJpaRepository;
    private final DiagnosticoMapper diagnosticoMapper;

    public DiagnosticoRepositoryAdapter(DiagnosticoJpaRepository diagnosticoJpaRepository,
                                        DiagnosticoMapper diagnosticoMapper) {
        this.diagnosticoJpaRepository = diagnosticoJpaRepository;
        this.diagnosticoMapper = diagnosticoMapper;
    }

    @Override
    public Diagnostico save(Diagnostico diagnostico) {
        DiagnosticoEntity entity = diagnosticoMapper.toEntity(diagnostico);
        DiagnosticoEntity savedEntity = diagnosticoJpaRepository.save(entity);
        return diagnosticoMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Diagnostico> findById(Long id) {
        return diagnosticoJpaRepository.findById(id)
                .map(diagnosticoMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        diagnosticoJpaRepository.deleteById(id);
    }
}