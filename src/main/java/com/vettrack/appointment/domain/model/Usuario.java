package com.vettrack.appointment.domain.model;

import com.vettrack.appointment.domain.enums.Role;
import com.vettrack.appointment.domain.exception.DomainException;

import java.util.HashSet;
import java.util.Set;

public class Usuario {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String documentoIdentidad;
    private Set<Role> roles;
    private Boolean activo;

    // Constructor vacío
    public Usuario() {
        this.roles = new HashSet<>();
        this.activo = true;
    }

    // Constructor completo
    public Usuario(Long id, String username, String password,
                   String email, String documentoIdentidad,
                   Set<Role> roles, Boolean activo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.documentoIdentidad = documentoIdentidad;
        this.roles = roles != null ? roles : new HashSet<>();
        this.activo = activo != null ? activo : true;
    }

    // ✅ LÓGICA DE NEGOCIO

    public void agregarRole(Role role) {
        this.roles.add(role);
    }

    public void removerRole(Role role) {
        this.roles.remove(role);
    }

    public boolean tieneRole(Role role) {
        return this.roles.contains(role);
    }

    public boolean esAdmin() {
        return this.roles.contains(Role.ROLE_ADMIN);
    }

    public boolean esDueno() {
        return this.roles.contains(Role.ROLE_DUENO);
    }

    public boolean esVeterinario() {
        return this.roles.contains(Role.ROLE_VETERINARIO);
    }

    public void activar() {
        this.activo = true;
    }

    public void desactivar() {
        this.activo = false;
    }

    public boolean estaActivo() {
        return this.activo;
    }

    public void validarDatos() {
        if (username == null || username.isBlank()) {
            throw new DomainException("El username es obligatorio");
        }

        if (password == null || password.isBlank()) {
            throw new DomainException("El password es obligatorio");
        }

        if (email == null || email.isBlank()) {
            throw new DomainException("El email es obligatorio");
        }

        if (roles == null || roles.isEmpty()) {
            throw new DomainException("El usuario debe tener al menos un rol");
        }
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}