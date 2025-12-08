package com.vettrack.appointment.infrastructure.adapter.out.persistence.mapper;

import com.vettrack.appointment.domain.model.Usuario;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    /**
     * Convierte de Domain → Entity
     */
    public UsuarioEntity toEntity(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.getId());
        entity.setUsername(usuario.getUsername());
        entity.setPassword(usuario.getPassword());
        entity.setEmail(usuario.getEmail());
        entity.setDocumentoIdentidad(usuario.getDocumentoIdentidad());
        entity.setRoles(usuario.getRoles());
        entity.setActivo(usuario.getActivo());

        return entity;
    }

    /**
     * Convierte de Entity → Domain
     */
    public Usuario toDomain(UsuarioEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Usuario(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getDocumentoIdentidad(),
                entity.getRoles(),
                entity.getActivo()
        );
    }
}