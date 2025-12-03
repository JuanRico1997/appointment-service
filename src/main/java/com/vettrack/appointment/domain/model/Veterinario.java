package com.vettrack.appointment.domain.model;

import com.vettrack.appointment.domain.exception.DomainException;

public class Veterinario {

    private Long id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String licencia;
    private String telefono;
    private String email;
    private Boolean activo;

    // Constructor vacío
    public Veterinario() {
        this.activo = true; // Por defecto activo
    }

    // Constructor completo
    public Veterinario(Long id, String nombre, String apellido,
                       String especialidad, String licencia,
                       String telefono, String email, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.licencia = licencia;
        this.telefono = telefono;
        this.email = email;
        this.activo = activo != null ? activo : true;
    }

    // ✅ LÓGICA DE NEGOCIO

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
        if (nombre == null || nombre.isBlank()) {
            throw new DomainException("El nombre del veterinario es obligatorio");
        }

        if (apellido == null || apellido.isBlank()) {
            throw new DomainException("El apellido del veterinario es obligatorio");
        }

        if (licencia == null || licencia.isBlank()) {
            throw new DomainException("La licencia profesional es obligatoria");
        }
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}