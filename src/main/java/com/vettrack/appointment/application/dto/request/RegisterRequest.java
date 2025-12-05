package com.vettrack.appointment.application.dto.request;

import com.vettrack.appointment.domain.enums.Role;

import java.util.Set;

public class RegisterRequest {

    private String username;
    private String password;
    private String email;
    private String documentoIdentidad;
    private Set<Role> roles;

    // Constructor vacío
    public RegisterRequest() {
    }

    // Constructor completo
    public RegisterRequest(String username, String password, String email,
                           String documentoIdentidad, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.documentoIdentidad = documentoIdentidad;
        this.roles = roles;
    }

    // Getters y Setters

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
}
