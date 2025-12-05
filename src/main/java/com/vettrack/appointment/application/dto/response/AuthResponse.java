package com.vettrack.appointment.application.dto.response;

import com.vettrack.appointment.domain.enums.Role;

import java.util.Set;

public class AuthResponse {

    private String token;
    private String type = "Bearer";
    private Long userId;
    private String username;
    private Set<Role> roles;

    // Constructor vacío
    public AuthResponse() {
    }

    // Constructor completo
    public AuthResponse(String token, Long userId, String username, Set<Role> roles) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.roles = roles;
    }

    // Getters y Setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}