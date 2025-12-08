package com.vettrack.appointment.infrastructure.adapter.out.persistence.entity;

import com.vettrack.appointment.domain.enums.Especie;
import com.vettrack.appointment.domain.enums.EstadoMascota;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mascotas")
public class MascotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Especie especie;

    private String raza;

    @Column(nullable = false)
    private Integer edad;

    @Column(name = "nombre_dueno", nullable = false)
    private String nombreDueno;

    @Column(name = "documento_dueno", nullable = false)
    private String documentoDueno;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoMascota estado;

    // Relación 1-N con Citas
    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL)
    private List<CitaEntity> citas = new ArrayList<>();

    // Constructor vacío (requerido por JPA)
    public MascotaEntity() {
    }

    // Constructor completo
    public MascotaEntity(Long id, String nombre, Especie especie, String raza,
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

    public List<CitaEntity> getCitas() {
        return citas;
    }

    public void setCitas(List<CitaEntity> citas) {
        this.citas = citas;
    }
}