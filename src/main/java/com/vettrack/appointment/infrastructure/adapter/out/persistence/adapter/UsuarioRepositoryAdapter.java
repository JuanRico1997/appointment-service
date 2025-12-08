package com.vettrack.appointment.infrastructure.adapter.out.persistence.adapter;

import com.vettrack.appointment.domain.model.Usuario;
import com.vettrack.appointment.domain.ports.out.UsuarioRepositoryPort;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.mapper.UsuarioMapper;
import com.vettrack.appointment.infrastructure.adapter.out.persistence.repository.UsuarioJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository usuarioJpaRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioRepositoryAdapter(UsuarioJpaRepository usuarioJpaRepository,
                                    UsuarioMapper usuarioMapper) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = usuarioMapper.toEntity(usuario);
        UsuarioEntity savedEntity = usuarioJpaRepository.save(entity);
        return usuarioMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioJpaRepository.findById(id)
                .map(usuarioMapper::toDomain);
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return usuarioJpaRepository.findByUsername(username)
                .map(usuarioMapper::toDomain);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioJpaRepository.findByEmail(email)
                .map(usuarioMapper::toDomain);
    }

    @Override
    public Optional<Usuario> findByDocumentoIdentidad(String documentoIdentidad) {
        return usuarioJpaRepository.findByDocumentoIdentidad(documentoIdentidad)
                .map(usuarioMapper::toDomain);
    }

    @Override
    public boolean existsByUsername(String username) {
        return usuarioJpaRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioJpaRepository.existsByEmail(email);
    }

    @Override
    public void deleteById(Long id) {
        usuarioJpaRepository.deleteById(id);
    }
}