package com.vettrack.appointment.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "veterinarios")
public class VeterinarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    private String especialidad;

    @Column(nullable = false, unique = true)
    private String licencia;

    private String telefono;

    private String email;

    @Column(nullable = false)
    private Boolean activo = true;

    // Relación 1-N con Citas
    @OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL)
    private List<CitaEntity> citas = new ArrayList<>();

    // Constructor vacío
    public VeterinarioEntity() {
    }

    // Constructor completo
    public VeterinarioEntity(Long id, String nombre, String apellido,
                             String especialidad, String licencia,
                             String telefono, String email, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.licencia = licencia;
        this.telefono = telefono;
        this.email = email;
        this.activo = activo;
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

    public List<CitaEntity> getCitas() {
        return citas;
    }

    public void setCitas(List<CitaEntity> citas) {
        this.citas = citas;
    }
}