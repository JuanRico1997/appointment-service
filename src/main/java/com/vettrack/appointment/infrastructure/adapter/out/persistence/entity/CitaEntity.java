package com.vettrack.appointment.infrastructure.adapter.out.persistence.entity;

import com.vettrack.appointment.domain.enums.EstadoCita;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "citas")
public class CitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación N-1 con Mascota
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mascota_id", nullable = false)
    private MascotaEntity mascota;

    // Relación N-1 con Veterinario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veterinario_id", nullable = false)
    private VeterinarioEntity veterinario;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime hora;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCita estado;

    // Relación 1-1 con Diagnostico
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "diagnostico_id", referencedColumnName = "id")
    private DiagnosticoEntity diagnostico;

    // Constructor vacío
    public CitaEntity() {
    }

    // Constructor completo
    public CitaEntity(Long id, MascotaEntity mascota, VeterinarioEntity veterinario,
                      LocalDate fecha, LocalTime hora, String motivo,
                      EstadoCita estado, DiagnosticoEntity diagnostico) {
        this.id = id;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.estado = estado;
        this.diagnostico = diagnostico;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MascotaEntity getMascota() {
        return mascota;
    }

    public void setMascota(MascotaEntity mascota) {
        this.mascota = mascota;
    }

    public VeterinarioEntity getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(VeterinarioEntity veterinario) {
        this.veterinario = veterinario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }

    public DiagnosticoEntity getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(DiagnosticoEntity diagnostico) {
        this.diagnostico = diagnostico;
    }
}