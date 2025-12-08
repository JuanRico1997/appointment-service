package com.vettrack.appointment.infrastructure.adapter.out.persistence.repository;

import com.vettrack.appointment.infrastructure.adapter.out.persistence.entity.VeterinarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeterinarioJpaRepository extends JpaRepository<VeterinarioEntity, Long> {

    List<VeterinarioEntity> findByActivo(Boolean activo);
}