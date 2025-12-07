package com.vettrack.appointment.application.service;

import com.vettrack.appointment.domain.enums.EstadoCita;
import com.vettrack.appointment.domain.exception.CitaNotFoundException;
import com.vettrack.appointment.domain.model.Cita;
import com.vettrack.appointment.domain.model.Mascota;
import com.vettrack.appointment.domain.model.Veterinario;
import com.vettrack.appointment.domain.ports.in.SolicitarCitaUseCase;
import com.vettrack.appointment.domain.ports.out.CitaRepositoryPort;
import com.vettrack.appointment.domain.ports.out.MascotaRepositoryPort;
import com.vettrack.appointment.domain.ports.out.VetAvailabilityPort;
import com.vettrack.appointment.domain.ports.out.VeterinarioRepositoryPort;
import com.vettrack.appointment.domain.service.CitaDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional
public class SolicitarCitaService implements SolicitarCitaUseCase {

    private final CitaRepositoryPort citaRepositoryPort;
    private final MascotaRepositoryPort mascotaRepositoryPort;
    private final VeterinarioRepositoryPort veterinarioRepositoryPort;
    private final VetAvailabilityPort vetAvailabilityPort;
    private final CitaDomainService citaDomainService;

    public SolicitarCitaService(CitaRepositoryPort citaRepositoryPort,
                                MascotaRepositoryPort mascotaRepositoryPort,
                                VeterinarioRepositoryPort veterinarioRepositoryPort,
                                VetAvailabilityPort vetAvailabilityPort,
                                CitaDomainService citaDomainService) {
        this.citaRepositoryPort = citaRepositoryPort;
        this.mascotaRepositoryPort = mascotaRepositoryPort;
        this.veterinarioRepositoryPort = veterinarioRepositoryPort;
        this.vetAvailabilityPort = vetAvailabilityPort;
        this.citaDomainService = citaDomainService;
    }

    @Override
    public Cita solicitar(Long mascotaId, Long veterinarioId,
                          LocalDate fecha, LocalTime hora, String motivo) {

        // 1. Buscar mascota
        Mascota mascota = mascotaRepositoryPort.findById(mascotaId)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + mascotaId));

        // 2. Buscar veterinario
        Veterinario veterinario = veterinarioRepositoryPort.findById(veterinarioId)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado con ID: " + veterinarioId));

        // 3. Validar todas las reglas de negocio
        citaDomainService.validarSolicitudCita(mascota, veterinario, fecha, hora);

        // 4. Llamar al servicio de disponibilidad (vet-availability-mock-service)
        VetAvailabilityPort.AvailabilityResponse disponibilidad =
                vetAvailabilityPort.checkAvailability(veterinarioId, fecha, hora);

        // 5. Validar disponibilidad
        citaDomainService.validarDisponibilidadVeterinario(
                disponibilidad.getDisponible(),
                disponibilidad.getMotivo()
        );

        // 6. Crear la cita
        Cita cita = new Cita();
        cita.setMascota(mascota);
        cita.setVeterinario(veterinario);
        cita.setFecha(fecha);
        cita.setHora(hora);
        cita.setMotivo(motivo);
        cita.setEstado(EstadoCita.PENDIENTE); // Estado inicial

        // Validar datos de la cita
        cita.validarDatos();

        // 7. Guardar y retornar
        return citaRepositoryPort.save(cita);
    }
}