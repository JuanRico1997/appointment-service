package com.vettrack.appointment.infrastructure.adapter.out.persistence.mapper;

import com.vettrack.appointment.domain.model.Mascota;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.entity.MascotaEntity;
import org.springframework.stereotype.Component;

@Component
public class MascotaMapper {

    /**
     * Convierte de Domain → Entity (para guardar en BD)
     */
    public MascotaEntity toEntity(Mascota mascota) {
        if (mascota == null) {
            return null;
        }

        MascotaEntity entity = new MascotaEntity();
        entity.setId(mascota.getId());
        entity.setNombre(mascota.getNombre());
        entity.setEspecie(mascota.getEspecie());
        entity.setRaza(mascota.getRaza());
        entity.setEdad(mascota.getEdad());
        entity.setNombreDueno(mascota.getNombreDueno());
        entity.setDocumentoDueno(mascota.getDocumentoDueno());
        entity.setEstado(mascota.getEstado());

        return entity;
    }

    /**
     * Convierte de Entity → Domain (para usar en lógica de negocio)
     */
    public Mascota toDomain(MascotaEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Mascota(
                entity.getId(),
                entity.getNombre(),
                entity.getEspecie(),
                entity.getRaza(),
                entity.getEdad(),
                entity.getNombreDueno(),
                entity.getDocumentoDueno(),
                entity.getEstado()
        );
    }
}