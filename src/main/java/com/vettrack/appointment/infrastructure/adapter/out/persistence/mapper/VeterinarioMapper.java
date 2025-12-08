package com.vettrack.appointment.infrastructure.adapter.out.persistence.mapper;

import com.vettrack.appointment.domain.model.Veterinario;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.entity.VeterinarioEntity;
import org.springframework.stereotype.Component;

@Component
public class VeterinarioMapper {

    /**
     * Convierte de Domain → Entity
     */
    public VeterinarioEntity toEntity(Veterinario veterinario) {
        if (veterinario == null) {
            return null;
        }

        VeterinarioEntity entity = new VeterinarioEntity();
        entity.setId(veterinario.getId());
        entity.setNombre(veterinario.getNombre());
        entity.setApellido(veterinario.getApellido());
        entity.setEspecialidad(veterinario.getEspecialidad());
        entity.setLicencia(veterinario.getLicencia());
        entity.setTelefono(veterinario.getTelefono());
        entity.setEmail(veterinario.getEmail());
        entity.setActivo(veterinario.getActivo());

        return entity;
    }

    /**
     * Convierte de Entity → Domain
     */
    public Veterinario toDomain(VeterinarioEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Veterinario(
                entity.getId(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getEspecialidad(),
                entity.getLicencia(),
                entity.getTelefono(),
                entity.getEmail(),
                entity.getActivo()
        );
    }
}