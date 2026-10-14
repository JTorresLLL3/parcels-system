package com.garden.parcel.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO para la creación y registro de una nueva Parcela.
 * Incluye validaciones declarativas de Jakarta Bean Validation.
 */
public class ParcelRequestDTO {

    @NotBlank(message = "El nombre de la parcela es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre de la parcela debe tener entre 3 y 100 caracteres")
    private String name;

    @NotBlank(message = "La ubicación GPS es obligatoria")
    @Pattern(
        regexp = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$",
        message = "La ubicación debe contener coordenadas GPS válidas en formato 'latitud,longitud' (ej: 19.4326,-99.1332)"
    )
    private String location;

    @NotBlank(message = "El tipo de suelo es obligatorio (arcilloso, limoso, arenoso)")
    private String soilType;

    @NotBlank(message = "El responsable o líder de la parcela es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre del responsable debe tener entre 3 y 100 caracteres")
    private String ownerOrLead;

    public ParcelRequestDTO() {
    }

    public ParcelRequestDTO(String name, String location, String soilType, String ownerOrLead) {
        this.name = name;
        this.location = location;
        this.soilType = soilType;
        this.ownerOrLead = ownerOrLead;
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
}
