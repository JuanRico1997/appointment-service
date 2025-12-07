package com.vettrack.appointment.application.service;

import com.vettrack.appointment.application.dto.request.MascotaRequest;
import com.vettrack.appointment.application.dto.response.MascotaResponse;
import com.vettrack.appointment.domain.enums.EstadoMascota;
import com.vettrack.appointment.domain.model.Mascota;
import com.vettrack.appointment.domain.ports.in.RegistrarMascotaUseCase;
import com.vettrack.appointment.domain.ports.out.MascotaRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrarMascotaService implements RegistrarMascotaUseCase {

    private final MascotaRepositoryPort mascotaRepositoryPort;

    public RegistrarMascotaService(MascotaRepositoryPort mascotaRepositoryPort) {
        this.mascotaRepositoryPort = mascotaRepositoryPort;
    }

    @Override
    public Mascota registrar(Mascota mascota) {
        // Validar datos del dominio
        mascota.validarDatos();

        // Guardar en BD
        return mascotaRepositoryPort.save(mascota);
    }

    @Override
    public Mascota actualizarEstado(Long mascotaId, boolean activa) {
        // Buscar mascota
        Mascota mascota = mascotaRepositoryPort.findById(mascotaId)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + mascotaId));

        // Cambiar estado según el parámetro
        if (activa) {
            mascota.activar();
        } else {
            mascota.desactivar();
        }

        // Guardar cambios
        return mascotaRepositoryPort.save(mascota);
    }

    // Método auxiliar para convertir Request → Domain
    public Mascota fromRequest(MascotaRequest request) {
        Mascota mascota = new Mascota();
        mascota.setNombre(request.getNombre());
        mascota.setEspecie(request.getEspecie());
        mascota.setRaza(request.getRaza());
        mascota.setEdad(request.getEdad());
        mascota.setNombreDueno(request.getNombreDueno());
        mascota.setDocumentoDueno(request.getDocumentoDueno());
        mascota.setEstado(EstadoMascota.ACTIVA); // Por defecto ACTIVA
        return mascota;
    }

    // Método auxiliar para convertir Domain → Response
    public MascotaResponse toResponse(Mascota mascota) {
        return new MascotaResponse(
                mascota.getId(),
                mascota.getNombre(),
                mascota.getEspecie(),
                mascota.getRaza(),
                mascota.getEdad(),
                mascota.getNombreDueno(),
                mascota.getDocumentoDueno(),
                mascota.getEstado()
        );
    }
}