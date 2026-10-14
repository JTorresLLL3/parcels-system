package com.cmp_parcel.controller;

import com.cmp_parcel.dto.ParcelDto;
import com.cmp_parcel.service.ParcelComposeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controlador del microservicio compuesto para exponer y orquestar las parcelas.
 */
@RestController
@RequestMapping("/parcel")
public class ParcelComposeController {

    private final ParcelComposeService parcelComposeService;

    public ParcelComposeController(ParcelComposeService parcelComposeService) {
        this.parcelComposeService = parcelComposeService;
    }

    @GetMapping
    public Flux<ParcelDto> findAll() {
        return parcelComposeService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ParcelDto> findById(@PathVariable Long id) {
        return parcelComposeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ParcelDto> create(@RequestBody ParcelDto parcelDto) {
        return parcelComposeService.create(parcelDto);
    }
}
