package com.vettrack.appointment.application.dto.response;

import com.vettrack.appointment.domain.enums.Role;

import java.util.Set;

public class UsuarioResponse {

    private Long id;
    private String username;
    private String email;
    private String documentoIdentidad;
    private Set<Role> roles;
    private Boolean activo;

    // Constructor vacío
    public UsuarioResponse() {
    }

    // Constructor completo
    public UsuarioResponse(Long id, String username, String email,
                           String documentoIdentidad, Set<Role> roles, Boolean activo) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.documentoIdentidad = documentoIdentidad;
        this.roles = roles;
        this.activo = activo;
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