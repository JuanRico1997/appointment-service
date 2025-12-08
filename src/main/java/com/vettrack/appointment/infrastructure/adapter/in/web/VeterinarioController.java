package com.vettrack.appointment.infrastructure.adapter.in.web;

import com.vettrack.appointment.application.dto.response.VeterinarioResponse;
import com.vettrack.appointment.domain.model.Veterinario;
import com.vettrack.appointment.domain.ports.out.VeterinarioRepositoryPort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/veterinarios")
public class VeterinarioController {

    private final VeterinarioRepositoryPort veterinarioRepositoryPort;

    public VeterinarioController(VeterinarioRepositoryPort veterinarioRepositoryPort) {
        this.veterinarioRepositoryPort = veterinarioRepositoryPort;
    }

    /**
     * Obtener todos los veterinarios
     * GET /api/veterinarios
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('DUENO', 'VETERINARIO', 'ADMIN')")
    public ResponseEntity<List<VeterinarioResponse>> obtenerTodosLosVeterinarios() {

        List<Veterinario> veterinarios = veterinarioRepositoryPort.findAll();

        List<VeterinarioResponse> responses = veterinarios.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    /**
     * Obtener veterinarios activos
     * GET /api/veterinarios/activos
     */
    @GetMapping("/activos")
    @PreAuthorize("hasAnyRole('DUENO', 'VETERINARIO', 'ADMIN')")
    public ResponseEntity<List<VeterinarioResponse>> obtenerVeterinariosActivos() {

        List<Veterinario> veterinarios = veterinarioRepositoryPort.findByActivo(true);

        List<VeterinarioResponse> responses = veterinarios.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    /**
     * Obtener veterinario por ID
     * GET /api/veterinarios/{id}
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('DUENO', 'VETERINARIO', 'ADMIN')")
    public ResponseEntity<VeterinarioResponse> obtenerVeterinarioPorId(@PathVariable Long id) {

        Veterinario veterinario = veterinarioRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado con ID: " + id));

        VeterinarioResponse response = toResponse(veterinario);

        return ResponseEntity.ok(response);
    }

    // Método auxiliar para convertir Domain → Response
    private VeterinarioResponse toResponse(Veterinario veterinario) {
        return new VeterinarioResponse(
                veterinario.getId(),
                veterinario.getNombre(),
                veterinario.getApellido(),
                veterinario.getEspecialidad(),
                veterinario.getLicencia(),
                veterinario.getTelefono(),
                veterinario.getEmail(),
                veterinario.getActivo()
        );
    }
}