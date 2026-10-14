package com.garden.parcel.repository;

import com.garden.parcel.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio Spring Data JPA para la entidad Parcel.
 */
@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {

    /**
     * Verifica si ya existe una parcela con el mismo nombre (ignora mayúsculas/minúsculas).
     */
    boolean existsByNameIgnoreCase(String name);

    /**
     * Busca una parcela por nombre exacto (ignora mayúsculas/minúsculas).
     */
    Optional<Parcel> findByNameIgnoreCase(String name);
}
