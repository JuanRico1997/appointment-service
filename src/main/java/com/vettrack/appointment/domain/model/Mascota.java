package com.vettrack.appointment.domain.model;

import com.vettrack.appointment.domain.enums.Especie;
import com.vettrack.appointment.domain.enums.EstadoMascota;
import com.vettrack.appointment.domain.exception.DomainException;

public class Mascota {

    private Long id;
    private String nombre;
    private Especie especie;
    private String raza;
    private Integer edad;
    private String nombreDueno;
    private String documentoDueno;
    private EstadoMascota estado;

    // Constructor vacío
    public Mascota() {
        this.estado = EstadoMascota.ACTIVA; // Por defecto ACTIVA
    }

    // Constructor completo
    public Mascota(Long id, String nombre, Especie especie, String raza,
                   Integer edad, String nombreDueno, String documentoDueno,
                   EstadoMascota estado) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.nombreDueno = nombreDueno;
        this.documentoDueno = documentoDueno;
        this.estado = estado != null ? estado : EstadoMascota.ACTIVA;
    }

    // ✅ LÓGICA DE NEGOCIO

    public void activar() {
        this.estado = EstadoMascota.ACTIVA;
    }

    public void desactivar() {
        this.estado = EstadoMascota.INACTIVA;
    }

    public boolean puedeReservarCita() {
        return this.estado == EstadoMascota.ACTIVA;
    }

    public void validarDatos() {
        if (documentoDueno == null || documentoDueno.isBlank()) {
            throw new DomainException("Documento del dueno es obligatorio");
        }

        if (edad == null || edad <= 0) {
            throw new DomainException("La edad debe ser mayor a 0");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new DomainException("El nombre es obligatorio");
        }
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

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public String getDocumentoDueno() {
        return documentoDueno;
    }

    public void setDocumentoDueno(String documentoDueno) {
        this.documentoDueno = documentoDueno;
    }

    public EstadoMascota getEstado() {
        return estado;
    }

    public void setEstado(EstadoMascota estado) {
        this.estado = estado;
    }
}