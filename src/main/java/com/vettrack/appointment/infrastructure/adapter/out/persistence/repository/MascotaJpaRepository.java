package com.vettrack.appointment.infrastructure.adapter.out.persistence.repository;

import com.vettrack.appointment.infrastructure.adapter.out.persistence.entity.MascotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaJpaRepository extends JpaRepository<MascotaEntity, Long> {

    List<MascotaEntity> findByDocumentoDueno(String documentoDueno);
}