package com.garden.parcel.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa una Parcela o Zona de Cultivo monitoreada.
 */
@Entity
@Table(name = "parcels")
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "soil_type", nullable = false, length = 20)
    private SoilType soilType;

    @Column(name = "owner_or_lead", nullable = false, length = 100)
    private String ownerOrLead;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Parcel() {
    }

    public Parcel(String name, String location, SoilType soilType, String ownerOrLead) {
        this.name = name;
        this.location = location;
        this.soilType = soilType;
        this.ownerOrLead = ownerOrLead;
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public SoilType getSoilType() {
        return soilType;
    }

    public void setSoilType(SoilType soilType) {
        this.soilType = soilType;
    }

    public String getOwnerOrLead() {
        return ownerOrLead;
    }

    public void setOwnerOrLead(String ownerOrLead) {
        this.ownerOrLead = ownerOrLead;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
