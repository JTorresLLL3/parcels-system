package com.cmp_parcel.service;

import com.cmp_parcel.dto.ParcelDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ParcelComposeService {

    @Value("${url.base.parcel:http://localhost:8081/api/v1/parcels}")
    private String urlBase;

    private final WebClient webClient;

    public ParcelComposeService(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Consume el microservicio base para listar todas las parcelas.
     */
    public Flux<ParcelDto> findAll() {
        return webClient.get()
                .uri(urlBase)
                .retrieve()
                .bodyToFlux(ParcelDto.class);
    }

    /**
     * Consume el microservicio base para consultar una parcela por su ID.
     */
    public Mono<ParcelDto> findById(Long id) {
        return webClient.get()
                .uri(urlBase + "/" + id)
                .retrieve()
                .bodyToMono(ParcelDto.class);
    }

    /**
     * Consume el microservicio base para registrar una nueva parcela.
     */
    public Mono<ParcelDto> create(ParcelDto parcelDto) {
        return webClient.post()
                .uri(urlBase)
                .bodyValue(parcelDto)
                .retrieve()
                .bodyToMono(ParcelDto.class);
    }
}
