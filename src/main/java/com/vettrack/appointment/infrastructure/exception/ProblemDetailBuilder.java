package com.vettrack.appointment.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.time.Instant;
import java.util.UUID;

public class ProblemDetailBuilder {

    /**
     * Crea un ProblemDetail estándar RFC 7807
     */
    public static ProblemDetail build(HttpStatus status, String title, String detail, String instance) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);

        problemDetail.setType(URI.create("https://api.vettrack.com/errors/" + status.name().toLowerCase()));
        problemDetail.setTitle(title);
        problemDetail.setInstance(URI.create(instance));

        // Propiedades adicionales
        problemDetail.setProperty("timestamp", Instant.now().toString());
        problemDetail.setProperty("traceId", UUID.randomUUID().toString());

        return problemDetail;
    }

    /**
     * Crea un ProblemDetail para errores de validación
     */
    public static ProblemDetail buildValidationError(String detail, String instance) {
        return build(
                HttpStatus.BAD_REQUEST,
                "Validation Error",
                detail,
                instance
        );
    }

    /**
     * Crea un ProblemDetail para recursos no encontrados
     */
    public static ProblemDetail buildNotFound(String detail, String instance) {
        return build(
                HttpStatus.NOT_FOUND,
                "Resource Not Found",
                detail,
                instance
        );
    }

    /**
     * Crea un ProblemDetail para errores de negocio
     */
    public static ProblemDetail buildBusinessError(String detail, String instance) {
        return build(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Business Rule Violation",
                detail,
                instance
        );
    }

    /**
     * Crea un ProblemDetail para errores de autenticación
     */
    public static ProblemDetail buildUnauthorized(String detail, String instance) {
        return build(
                HttpStatus.UNAUTHORIZED,
                "Authentication Failed",
                detail,
                instance
        );
    }

    /**
     * Crea un ProblemDetail para errores de permisos
     */
    public static ProblemDetail buildForbidden(String detail, String instance) {
        return build(
                HttpStatus.FORBIDDEN,
                "Access Denied",
                detail,
                instance
        );
    }
}