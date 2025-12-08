package com.vettrack.appointment.infrastructure.adapter.out.external;

import com.vettrack.appointment.domain.ports.out.VetAvailabilityPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class VetAvailabilityAdapter implements VetAvailabilityPort {

    private final RestTemplate restTemplate;
    private final String vetAvailabilityServiceUrl;

    public VetAvailabilityAdapter(RestTemplate restTemplate,
                                  @Value("${vet.availability.service.url}") String vetAvailabilityServiceUrl) {
        this.restTemplate = restTemplate;
        this.vetAvailabilityServiceUrl = vetAvailabilityServiceUrl;
    }

    @Override
    public AvailabilityResponse checkAvailability(Long veterinarioId, LocalDate fecha, LocalTime hora) {

        // 1. Construir el cuerpo de la petición
        Map<String, Object> request = new HashMap<>();
        request.put("veterinarioId", veterinarioId);
        request.put("fecha", fecha.toString());
        request.put("hora", hora.toString());

        try {
            // 2. Hacer la petición POST al mock-service
            String url = vetAvailabilityServiceUrl + "/availability";

            AvailabilityResponseDto response = restTemplate.postForObject(
                    url,
                    request,
                    AvailabilityResponseDto.class
            );

            // 3. Convertir DTO → AvailabilityResponse
            if (response != null) {
                return new AvailabilityResponse(
                        response.getVeterinarioId(),
                        response.getDisponible(),
                        response.getMotivo()
                );
            }

            // 4. Si la respuesta es null, asumir no disponible
            return new AvailabilityResponse(
                    veterinarioId,
                    false,
                    "Error al consultar disponibilidad"
            );

        } catch (Exception e) {
            // 5. En caso de error (servicio caído, timeout, etc.)
            return new AvailabilityResponse(
                    veterinarioId,
                    false,
                    "Servicio de disponibilidad no responde: " + e.getMessage()
            );
        }
    }

    /**
     * DTO interno para mapear la respuesta del servicio externo
     */
    private static class AvailabilityResponseDto {
        private Long veterinarioId;
        private Boolean disponible;
        private String motivo;

        public AvailabilityResponseDto() {
        }

        public Long getVeterinarioId() {
            return veterinarioId;
        }

        public void setVeterinarioId(Long veterinarioId) {
            this.veterinarioId = veterinarioId;
        }

        public Boolean getDisponible() {
            return disponible;
        }

        public void setDisponible(Boolean disponible) {
            this.disponible = disponible;
        }

        public String getMotivo() {
            return motivo;
        }

        public void setMotivo(String motivo) {
            this.motivo = motivo;
        }
    }
}