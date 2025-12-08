package com.vettrack.appointment.infrastructure.security;

import com.vettrack.appointment.domain.enums.Role;
import com.vettrack.appointment.domain.model.Usuario;
import com.vettrack.appointment.domain.ports.out.UsuarioRepositoryPort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public UserDetailsServiceImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Buscar usuario en la base de datos
        Usuario usuario = usuarioRepositoryPort.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado: " + username)
                );

        // Verificar si el usuario está activo
        if (!usuario.estaActivo()) {
            throw new UsernameNotFoundException("Usuario inactivo: " + username);
        }

        // Convertir roles del dominio a GrantedAuthority de Spring Security
        Collection<GrantedAuthority> authorities = mapRolesToAuthorities(usuario.getRoles());

        // Crear y retornar UserDetails de Spring Security
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!usuario.estaActivo())
                .build();
    }

    /**
     * Convierte los roles del dominio a GrantedAuthority de Spring Security
     */
    private Collection<GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }
}