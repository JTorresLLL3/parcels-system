package com.garden.parcel.controller;

import com.garden.parcel.dto.request.ParcelRequestDTO;
import com.garden.parcel.dto.response.ParcelResponseDTO;
import com.garden.parcel.service.ParcelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de Parcelas en la red de huertos urbanos.
 */
@RestController
@RequestMapping("/api/v1/parcels")
public class ParcelController {

    private final ParcelService parcelService;

    public ParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    /**
     * POST /api/v1/parcels
     * Registra una nueva parcela aplicando validaciones declarativas y sanitización.
     */
    @PostMapping
    public ResponseEntity<ParcelResponseDTO> createParcel(@Valid @RequestBody ParcelRequestDTO request) {
        ParcelResponseDTO created = parcelService.createParcel(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * GET /api/v1/parcels
     * Lista todas las parcelas registradas.
     */
    @GetMapping
    public ResponseEntity<List<ParcelResponseDTO>> getAllParcels() {
        List<ParcelResponseDTO> parcels = parcelService.getAllParcels();
        return ResponseEntity.ok(parcels);
    }

    /**
     * GET /api/v1/parcels/{id}
     * Consulta el detalle y estado de una parcela por su identificador.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ParcelResponseDTO> getParcelById(@PathVariable Long id) {
        ParcelResponseDTO parcel = parcelService.getParcelById(id);
        return ResponseEntity.ok(parcel);
    }
}
