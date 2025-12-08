package com.vettrack.appointment.infrastructure.adapter.in.security;

import com.vettrack.appointment.application.dto.request.LoginRequest;
import com.vettrack.appointment.application.dto.request.RegisterRequest;
import com.vettrack.appointment.application.dto.response.AuthResponse;
import com.vettrack.appointment.application.dto.response.UsuarioResponse;
import com.vettrack.appointment.domain.enums.Role;
import com.vettrack.appointment.domain.model.Usuario;
import com.vettrack.appointment.domain.ports.out.UsuarioRepositoryPort;
import com.vettrack.appointment.infrastructure.security.jwt.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager,
                          UsuarioRepositoryPort usuarioRepositoryPort,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Login - Autenticar usuario y generar JWT
     * POST /api/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {

        // 1. Autenticar con Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // 2. Establecer autenticación en el contexto
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. Generar token JWT
        String token = jwtTokenProvider.generateToken(authentication);

        // 4. Buscar usuario para obtener información adicional
        Usuario usuario = usuarioRepositoryPort.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 5. Crear respuesta
        AuthResponse response = new AuthResponse(
                token,
                usuario.getId(),
                usuario.getUsername(),
                usuario.getRoles()
        );

        return ResponseEntity.ok(response);
    }

    /**
     * Register - Registrar nuevo usuario
     * POST /api/auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {

        // 1. Verificar si el username ya existe
        if (usuarioRepositoryPort.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body("Error: El username ya está en uso");
        }

        // 2. Verificar si el email ya existe
        if (usuarioRepositoryPort.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest()
                    .body("Error: El email ya está en uso");
        }

        // 3. Asignar rol por defecto si no se especificó
        Set<Role> roles = registerRequest.getRoles();
        if (roles == null || roles.isEmpty()) {
            roles = new HashSet<>();
            roles.add(Role.ROLE_DUENO); // Rol por defecto
        }

        // 4. Crear usuario
        Usuario usuario = new Usuario(
                null,
                registerRequest.getUsername(),
                passwordEncoder.encode(registerRequest.getPassword()), // Encriptar password
                registerRequest.getEmail(),
                registerRequest.getDocumentoIdentidad(),
                roles,
                true
        );

        // 5. Validar y guardar
        usuario.validarDatos();
        Usuario usuarioGuardado = usuarioRepositoryPort.save(usuario);

        // 6. Crear respuesta
        UsuarioResponse response = new UsuarioResponse(
                usuarioGuardado.getId(),
                usuarioGuardado.getUsername(),
                usuarioGuardado.getEmail(),
                usuarioGuardado.getDocumentoIdentidad(),
                usuarioGuardado.getRoles(),
                usuarioGuardado.getActivo()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
