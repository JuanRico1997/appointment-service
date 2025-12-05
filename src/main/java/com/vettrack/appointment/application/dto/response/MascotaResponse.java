package com.vettrack.appointment.application.dto.response;

import com.vettrack.appointment.domain.enums.Especie;
import com.vettrack.appointment.domain.enums.EstadoMascota;

public class MascotaResponse {

    private Long id;
    private String nombre;
    private Especie especie;
    private String raza;
    private Integer edad;
    private String nombreDueno;
    private String documentoDueno;
    private EstadoMascota estado;

    // Constructor vacío
    public MascotaResponse() {
    }

    // Constructor completo
    public MascotaResponse(Long id, String nombre, Especie especie, String raza,
                           Integer edad, String nombreDueno, String documentoDueno,
                           EstadoMascota estado) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.nombreDueno = nombreDueno;
        this.documentoDueno = documentoDueno;
        this.estado = estado;
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