package com.garden.parcel.dto.response;

import java.time.LocalDateTime;

/**
 * DTO de respuesta para representar una Parcela y su estado actual.
 */
public class ParcelResponseDTO {

    private Long id;
    private String name;
    private String location;
    private String soilType;
    private String ownerOrLead;
    private LocalDateTime createdAt;
    private String status;

    public ParcelResponseDTO() {
    }

    public ParcelResponseDTO(Long id, String name, String location, String soilType, String ownerOrLead, LocalDateTime createdAt, String status) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.soilType = soilType;
        this.ownerOrLead = ownerOrLead;
        this.createdAt = createdAt;
        this.status = status;
    }

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

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
