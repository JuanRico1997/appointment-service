package com.vettrack.appointment.infrastructure.adapter.in.web;

import com.vettrack.appointment.application.dto.request.CitaRequest;
import com.vettrack.appointment.application.dto.response.CitaResponse;
import com.vettrack.appointment.application.dto.response.DiagnosticoResponse;
import com.vettrack.appointment.application.dto.response.MascotaResponse;
import com.vettrack.appointment.application.dto.response.VeterinarioResponse;
import com.vettrack.appointment.domain.model.Cita;
import com.vettrack.appointment.domain.ports.in.CancelarCitaUseCase;
import com.vettrack.appointment.domain.ports.in.ConfirmarCitaUseCase;
import com.vettrack.appointment.domain.ports.in.SolicitarCitaUseCase;
import com.vettrack.appointment.domain.ports.out.CitaRepositoryPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final SolicitarCitaUseCase solicitarCitaUseCase;
    private final ConfirmarCitaUseCase confirmarCitaUseCase;
    private final CancelarCitaUseCase cancelarCitaUseCase;
    private final CitaRepositoryPort citaRepositoryPort;

    public CitaController(SolicitarCitaUseCase solicitarCitaUseCase,
                          ConfirmarCitaUseCase confirmarCitaUseCase,
                          CancelarCitaUseCase cancelarCitaUseCase,
                          CitaRepositoryPort citaRepositoryPort) {
        this.solicitarCitaUseCase = solicitarCitaUseCase;
        this.confirmarCitaUseCase = confirmarCitaUseCase;
        this.cancelarCitaUseCase = cancelarCitaUseCase;
        this.citaRepositoryPort = citaRepositoryPort;
    }

    /**
     * Solicitar una nueva cita
     * POST /api/citas
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('DUENO', 'ADMIN')")
    public ResponseEntity<CitaResponse> solicitarCita(@RequestBody CitaRequest request) {

        // Solicitar cita (ejecuta toda la lógica: validaciones + llamada al mock service)
        Cita cita = solicitarCitaUseCase.solicitar(
                request.getMascotaId(),
                request.getVeterinarioId(),
                request.getFecha(),
                request.getHora(),
                request.getMotivo()
        );

        // Convertir Domain → Response
        CitaResponse response = toResponse(cita);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Confirmar una cita
     * PUT /api/citas/{id}/confirmar
     */
    @PutMapping("/{id}/confirmar")
    @PreAuthorize("hasAnyRole('VETERINARIO', 'ADMIN')")
    public ResponseEntity<CitaResponse> confirmarCita(@PathVariable Long id) {

        Cita cita = confirmarCitaUseCase.confirmar(id);
        CitaResponse response = toResponse(cita);

        return ResponseEntity.ok(response);
    }

    /**
     * Cancelar una cita
     * PUT /api/citas/{id}/cancelar
     */
    @PutMapping("/{id}/cancelar")
    @PreAuthorize("hasAnyRole('DUENO', 'VETERINARIO', 'ADMIN')")
    public ResponseEntity<CitaResponse> cancelarCita(@PathVariable Long id) {

        Cita cita = cancelarCitaUseCase.cancelar(id);
        CitaResponse response = toResponse(cita);

        return ResponseEntity.ok(response);
    }

    /**
     * Obtener todas las citas
     * GET /api/citas
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CitaResponse>> obtenerTodasLasCitas() {

        List<Cita> citas = citaRepositoryPort.findAll();

        List<CitaResponse> responses = citas.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    /**
     * Obtener cita por ID
     * GET /api/citas/{id}
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('DUENO', 'VETERINARIO', 'ADMIN')")
    public ResponseEntity<CitaResponse> obtenerCitaPorId(@PathVariable Long id) {

        Cita cita = citaRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));

        CitaResponse response = toResponse(cita);

        return ResponseEntity.ok(response);
    }

    /**
     * Obtener citas por mascota
     * GET /api/citas/mascota/{mascotaId}
     */
    @GetMapping("/mascota/{mascotaId}")
    @PreAuthorize("hasAnyRole('DUENO', 'ADMIN')")
    public ResponseEntity<List<CitaResponse>> obtenerCitasPorMascota(@PathVariable Long mascotaId) {

        List<Cita> citas = citaRepositoryPort.findByMascotaId(mascotaId);

        List<CitaResponse> responses = citas.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    /**
     * Obtener citas por veterinario
     * GET /api/citas/veterinario/{veterinarioId}
     */
    @GetMapping("/veterinario/{veterinarioId}")
    @PreAuthorize("hasAnyRole('VETERINARIO', 'ADMIN')")
    public ResponseEntity<List<CitaResponse>> obtenerCitasPorVeterinario(
            @PathVariable Long veterinarioId) {

        List<Cita> citas = citaRepositoryPort.findByVeterinarioId(veterinarioId);

        List<CitaResponse> responses = citas.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    /**
     * Obtener citas por documento del dueño
     * GET /api/citas/dueno/{documentoDueno}
     */
    @GetMapping("/dueno/{documentoDueno}")
    @PreAuthorize("hasAnyRole('DUENO', 'ADMIN')")
    public ResponseEntity<List<CitaResponse>> obtenerCitasPorDueno(
            @PathVariable String documentoDueno) {

        List<Cita> citas = citaRepositoryPort.findByDocumentoDueno(documentoDueno);

        List<CitaResponse> responses = citas.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    // Método auxiliar para convertir Domain → Response
    private CitaResponse toResponse(Cita cita) {

        MascotaResponse mascotaResponse = cita.getMascota() != null ?
                new MascotaResponse(
                        cita.getMascota().getId(),
                        cita.getMascota().getNombre(),
                        cita.getMascota().getEspecie(),
                        cita.getMascota().getRaza(),
                        cita.getMascota().getEdad(),
                        cita.getMascota().getNombreDueno(),
                        cita.getMascota().getDocumentoDueno(),
                        cita.getMascota().getEstado()
                ) : null;

        VeterinarioResponse veterinarioResponse = cita.getVeterinario() != null ?
                new VeterinarioResponse(
                        cita.getVeterinario().getId(),
                        cita.getVeterinario().getNombre(),
                        cita.getVeterinario().getApellido(),
                        cita.getVeterinario().getEspecialidad(),
                        cita.getVeterinario().getLicencia(),
                        cita.getVeterinario().getTelefono(),
                        cita.getVeterinario().getEmail(),
                        cita.getVeterinario().getActivo()
                ) : null;

        DiagnosticoResponse diagnosticoResponse = cita.getDiagnostico() != null ?
                new DiagnosticoResponse(
                        cita.getDiagnostico().getId(),
                        cita.getDiagnostico().getDescripcion(),
                        cita.getDiagnostico().getTratamientoSugerido(),
                        cita.getDiagnostico().getRecomendaciones()
                ) : null;

        return new CitaResponse(
                cita.getId(),
                mascotaResponse,
                veterinarioResponse,
                cita.getFecha(),
                cita.getHora(),
                cita.getMotivo(),
                cita.getEstado(),
                diagnosticoResponse
        );
    }
}