package com.vettrack.appointment.infrastructure.exception;

import com.vettrack.appointment.domain.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones de dominio generales
     */
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ProblemDetail> handleDomainException(
            DomainException ex, HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetailBuilder.buildBusinessError(
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(problemDetail);
    }

    /**
     * Maneja excepciones de mascota no activa
     */
    @ExceptionHandler(MascotaNoActivaException.class)
    public ResponseEntity<ProblemDetail> handleMascotaNoActivaException(
            MascotaNoActivaException ex, HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetailBuilder.buildBusinessError(
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(problemDetail);
    }

    /**
     * Maneja excepciones de veterinario no disponible
     */
    @ExceptionHandler(VeterinarioNoDisponibleException.class)
    public ResponseEntity<ProblemDetail> handleVeterinarioNoDisponibleException(
            VeterinarioNoDisponibleException ex, HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetailBuilder.buildBusinessError(
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(problemDetail);
    }

    /**
     * Maneja excepciones de cita no encontrada
     */
    @ExceptionHandler(CitaNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleCitaNotFoundException(
            CitaNotFoundException ex, HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetailBuilder.buildNotFound(
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(problemDetail);
    }

    /**
     * Maneja excepciones de horario inválido
     */
    @ExceptionHandler(HorarioInvalidoException.class)
    public ResponseEntity<ProblemDetail> handleHorarioInvalidoException(
            HorarioInvalidoException ex, HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetailBuilder.buildValidationError(
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(problemDetail);
    }

    /**
     * Maneja excepciones de cita ya confirmada
     */
    @ExceptionHandler(CitaYaConfirmadaException.class)
    public ResponseEntity<ProblemDetail> handleCitaYaConfirmadaException(
            CitaYaConfirmadaException ex, HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetailBuilder.buildBusinessError(
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(problemDetail);
    }

    /**
     * Maneja excepciones de autenticación
     */
    @ExceptionHandler({AuthenticationException.class, BadCredentialsException.class})
    public ResponseEntity<ProblemDetail> handleAuthenticationException(
            Exception ex, HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetailBuilder.buildUnauthorized(
                "Credenciales incorrectas o usuario no encontrado",
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(problemDetail);
    }

    /**
     * Maneja excepciones de acceso denegado
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ProblemDetail> handleAccessDeniedException(
            AccessDeniedException ex, HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetailBuilder.buildForbidden(
                "No tiene permisos para acceder a este recurso",
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(problemDetail);
    }

    /**
     * Maneja excepciones de RuntimeException genéricas
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ProblemDetail> handleRuntimeException(
            RuntimeException ex, HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetailBuilder.build(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                ex.getMessage() != null ? ex.getMessage() : "Ha ocurrido un error inesperado",
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(problemDetail);
    }

    /**
     * Maneja cualquier otra excepción no controlada
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGenericException(
            Exception ex, HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetailBuilder.build(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                "Ha ocurrido un error inesperado en el servidor",
                request.getRequestURI()
        );

        // Logging del error (opcional)
        System.err.println("Error no controlado: " + ex.getMessage());
        ex.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(problemDetail);
    }
}
