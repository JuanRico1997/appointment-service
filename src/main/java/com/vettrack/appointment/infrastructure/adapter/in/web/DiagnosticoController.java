package com.vettrack.appointment.infrastructure.adapter.in.web;

import com.vettrack.appointment.application.dto.request.DiagnosticoRequest;
import com.vettrack.appointment.application.dto.response.CitaResponse;
import com.vettrack.appointment.application.dto.response.DiagnosticoResponse;
import com.vettrack.appointment.application.dto.response.MascotaResponse;
import com.vettrack.appointment.application.dto.response.VeterinarioResponse;
import com.vettrack.appointment.domain.model.Cita;
import com.vettrack.appointment.domain.model.Diagnostico;
import com.vettrack.appointment.domain.ports.in.RegistrarDiagnosticoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diagnosticos")
public class DiagnosticoController {

    private final RegistrarDiagnosticoUseCase registrarDiagnosticoUseCase;

    public DiagnosticoController(RegistrarDiagnosticoUseCase registrarDiagnosticoUseCase) {
        this.registrarDiagnosticoUseCase = registrarDiagnosticoUseCase;
    }

    /**
     * Registrar diagnóstico para una cita
     * POST /api/diagnosticos/cita/{citaId}
     */
    @PostMapping("/cita/{citaId}")
    @PreAuthorize("hasAnyRole('VETERINARIO', 'ADMIN')")
    public ResponseEntity<CitaResponse> registrarDiagnostico(
            @PathVariable Long citaId,
            @RequestBody DiagnosticoRequest request) {

        // Crear diagnóstico desde el request
        Diagnostico diagnostico = new Diagnostico(
                null,
                request.getDescripcion(),
                request.getTratamientoSugerido(),
                request.getRecomendaciones()
        );

        // Registrar diagnóstico
        Cita citaActualizada = registrarDiagnosticoUseCase.registrarDiagnostico(citaId, diagnostico);

        // Convertir a response
        CitaResponse response = toResponse(citaActualizada);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
