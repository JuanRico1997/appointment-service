package com.vettrack.appointment.domain.ports.in;

import com.vettrack.appointment.domain.model.Mascota;

public interface RegistrarMascotaUseCase {

    Mascota registrar(Mascota mascota);

    Mascota actualizarEstado(Long mascotaId, boolean activa);
}