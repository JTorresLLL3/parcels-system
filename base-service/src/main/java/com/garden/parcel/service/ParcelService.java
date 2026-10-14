package com.garden.parcel.service;

import com.garden.parcel.dto.request.ParcelRequestDTO;
import com.garden.parcel.dto.response.ParcelResponseDTO;
import com.garden.parcel.entity.Parcel;
import com.garden.parcel.entity.SoilType;
import com.garden.parcel.exception.ResourceNotFoundException;
import com.garden.parcel.repository.ParcelRepository;
import com.garden.parcel.util.SanitizerUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParcelService {

    private final ParcelRepository parcelRepository;

    public ParcelService(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    public ParcelResponseDTO createParcel(ParcelRequestDTO request) {
        // Sanitización de entradas (XSS y espacios)
        String name = SanitizerUtil.sanitizeText(request.getName());
        String owner = SanitizerUtil.sanitizeText(request.getOwnerOrLead());
        String location = SanitizerUtil.sanitizeCoordinates(request.getLocation());

        // Validación de tipo de suelo
        SoilType soilType = SoilType.fromString(request.getSoilType());
        if (soilType == null) {
            throw new IllegalArgumentException("Tipo de suelo inválido. Debe ser: arcilloso, limoso o arenoso");
        }

        Parcel parcel = new Parcel(name, location, soilType, owner);
        Parcel saved = parcelRepository.save(parcel);

        return mapToDTO(saved);
    }

    public List<ParcelResponseDTO> getAllParcels() {
        return parcelRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ParcelResponseDTO getParcelById(Long id) {
        Parcel parcel = parcelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parcela no encontrada con id: " + id));

        return mapToDTO(parcel);
    }

    private ParcelResponseDTO mapToDTO(Parcel parcel) {
        return new ParcelResponseDTO(
                parcel.getId(),
                parcel.getName(),
                parcel.getLocation(),
                parcel.getSoilType().getValue(),
                parcel.getOwnerOrLead(),
                parcel.getCreatedAt(),
                "MONITOREADA"
        );
    }
}
