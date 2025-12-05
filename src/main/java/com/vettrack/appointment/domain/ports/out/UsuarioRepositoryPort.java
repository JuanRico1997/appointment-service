package com.vettrack.appointment.domain.ports.out;

import com.vettrack.appointment.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {

    Usuario save(Usuario usuario);

    Optional<Usuario> findById(Long id);

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByDocumentoIdentidad(String documentoIdentidad);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void deleteById(Long id);
}