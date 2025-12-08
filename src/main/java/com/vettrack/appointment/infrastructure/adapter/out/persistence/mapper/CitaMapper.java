package com.vettrack.appointment.infrastructure.adapter.out.persistence.mapper;

import com.vettrack.appointment.domain.model.Cita;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.entity.CitaEntity;
import org.springframework.stereotype.Component;

@Component
public class CitaMapper {

    private final MascotaMapper mascotaMapper;
    private final VeterinarioMapper veterinarioMapper;
    private final DiagnosticoMapper diagnosticoMapper;

    public CitaMapper(MascotaMapper mascotaMapper,
                      VeterinarioMapper veterinarioMapper,
                      DiagnosticoMapper diagnosticoMapper) {
        this.mascotaMapper = mascotaMapper;
        this.veterinarioMapper = veterinarioMapper;
        this.diagnosticoMapper = diagnosticoMapper;
    }

    /**
     * Convierte de Domain → Entity
     */
    public CitaEntity toEntity(Cita cita) {
        if (cita == null) {
            return null;
        }

        CitaEntity entity = new CitaEntity();
        entity.setId(cita.getId());
        entity.setMascota(mascotaMapper.toEntity(cita.getMascota()));
        entity.setVeterinario(veterinarioMapper.toEntity(cita.getVeterinario()));
        entity.setFecha(cita.getFecha());
        entity.setHora(cita.getHora());
        entity.setMotivo(cita.getMotivo());
        entity.setEstado(cita.getEstado());
        entity.setDiagnostico(diagnosticoMapper.toEntity(cita.getDiagnostico()));

        return entity;
    }

    /**
     * Convierte de Entity → Domain
     */
    public Cita toDomain(CitaEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Cita(
                entity.getId(),
                mascotaMapper.toDomain(entity.getMascota()),
                veterinarioMapper.toDomain(entity.getVeterinario()),
                entity.getFecha(),
                entity.getHora(),
                entity.getMotivo(),
                entity.getEstado(),
                diagnosticoMapper.toDomain(entity.getDiagnostico())
        );
    }
}
