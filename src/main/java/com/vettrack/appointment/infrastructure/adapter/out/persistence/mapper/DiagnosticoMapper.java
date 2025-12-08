package com.vettrack.appointment.infrastructure.adapter.out.persistence.mapper;

import com.vettrack.appointment.domain.model.Diagnostico;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.entity.DiagnosticoEntity;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticoMapper {

    /**
     * Convierte de Domain → Entity
     */
    public DiagnosticoEntity toEntity(Diagnostico diagnostico) {
        if (diagnostico == null) {
            return null;
        }

        DiagnosticoEntity entity = new DiagnosticoEntity();
        entity.setId(diagnostico.getId());
        entity.setDescripcion(diagnostico.getDescripcion());
        entity.setTratamientoSugerido(diagnostico.getTratamientoSugerido());
        entity.setRecomendaciones(diagnostico.getRecomendaciones());

        return entity;
    }

    /**
     * Convierte de Entity → Domain
     */
    public Diagnostico toDomain(DiagnosticoEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Diagnostico(
                entity.getId(),
                entity.getDescripcion(),
                entity.getTratamientoSugerido(),
                entity.getRecomendaciones()
        );
    }
}