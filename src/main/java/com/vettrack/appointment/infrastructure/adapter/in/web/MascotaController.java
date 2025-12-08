package com.vettrack.appointment.infrastructure.adapter.in.web;

import com.vettrack.appointment.application.dto.request.MascotaRequest;
import com.vettrack.appointment.application.dto.response.MascotaResponse;
import com.vettrack.appointment.application.service.RegistrarMascotaService;
import com.vettrack.appointment.domain.model.Mascota;
import com.vettrack.appointment.domain.ports.out.MascotaRepositoryPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    private final RegistrarMascotaService registrarMascotaService;
    private final MascotaRepositoryPort mascotaRepositoryPort;

    public MascotaController(RegistrarMascotaService registrarMascotaService,
                             MascotaRepositoryPort mascotaRepositoryPort) {
        this.registrarMascotaService = registrarMascotaService;
        this.mascotaRepositoryPort = mascotaRepositoryPort;
    }

    /**
     * Registrar una nueva mascota
     * POST /api/mascotas
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('DUENO', 'ADMIN')")
    public ResponseEntity<MascotaResponse> registrarMascota(@RequestBody MascotaRequest request) {

        // Convertir Request → Domain
        Mascota mascota = registrarMascotaService.fromRequest(request);

        // Registrar
        Mascota mascotaRegistrada = registrarMascotaService.registrar(mascota);

        // Convertir Domain → Response
        MascotaResponse response = registrarMascotaService.toResponse(mascotaRegistrada);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Obtener todas las mascotas
     * GET /api/mascotas
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('DUENO', 'VETERINARIO', 'ADMIN')")
    public ResponseEntity<List<MascotaResponse>> obtenerTodasLasMascotas() {

        List<Mascota> mascotas = mascotaRepositoryPort.findAll();

        List<MascotaResponse> responses = mascotas.stream()
                .map(registrarMascotaService::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    /**
     * Obtener mascota por ID
     * GET /api/mascotas/{id}
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('DUENO', 'VETERINARIO', 'ADMIN')")
    public ResponseEntity<MascotaResponse> obtenerMascotaPorId(@PathVariable Long id) {

        Mascota mascota = mascotaRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + id));

        MascotaResponse response = registrarMascotaService.toResponse(mascota);

        return ResponseEntity.ok(response);
    }

    /**
     * Obtener mascotas por documento del dueño
     * GET /api/mascotas/dueno/{documentoDueno}
     */
    @GetMapping("/dueno/{documentoDueno}")
    @PreAuthorize("hasAnyRole('DUENO', 'ADMIN')")
    public ResponseEntity<List<MascotaResponse>> obtenerMascotasPorDueno(
            @PathVariable String documentoDueno) {

        List<Mascota> mascotas = mascotaRepositoryPort.findByDocumentoDueno(documentoDueno);

        List<MascotaResponse> responses = mascotas.stream()
                .map(registrarMascotaService::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    /**
     * Activar mascota
     * PUT /api/mascotas/{id}/activar
     */
    @PutMapping("/{id}/activar")
    @PreAuthorize("hasAnyRole('DUENO', 'ADMIN')")
    public ResponseEntity<MascotaResponse> activarMascota(@PathVariable Long id) {

        Mascota mascota = registrarMascotaService.actualizarEstado(id, true);
        MascotaResponse response = registrarMascotaService.toResponse(mascota);

        return ResponseEntity.ok(response);
    }

    /**
     * Desactivar mascota
     * PUT /api/mascotas/{id}/desactivar
     */
    @PutMapping("/{id}/desactivar")
    @PreAuthorize("hasAnyRole('DUENO', 'ADMIN')")
    public ResponseEntity<MascotaResponse> desactivarMascota(@PathVariable Long id) {

        Mascota mascota = registrarMascotaService.actualizarEstado(id, false);
        MascotaResponse response = registrarMascotaService.toResponse(mascota);

        return ResponseEntity.ok(response);
    }
}