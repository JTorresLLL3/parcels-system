# Red de Huertos Urbanos Comunitarios - Monorepo Microservicios

Sistema para la gestión y monitoreo de parcelas agrícolas urbanas mediante arquitectura de microservicios con Spring Boot.

---

## Estructura del Proyecto (Monorepo)

```
parcel/
├── .gitignore                          # Exclusiones de Git (Java, Maven, IDEs)
├── README.md                           # Documentación completa del proyecto
├── base-service/                       # Microservicio Base (Persistencia en PostgreSQL, Validaciones, Errores)
│   ├── pom.xml
│   └── src/
│       ├── main/java/com/garden/parcel/
│       │   ├── controller/             # ParcelController (/api/v1/parcels)
│       │   ├── dto/                    # RequestDTO, ResponseDTO, ErrorDTO
│       │   ├── entity/                 # Entidad Parcel, Enum SoilType
│       │   ├── exception/              # ResourceNotFoundException, GlobalExceptionHandler
│       │   ├── repository/             # ParcelRepository (Spring Data JPA)
│       │   ├── service/                # ParcelService, ParcelServiceImpl
│       │   └── util/                   # SanitizerUtil (XSS, normalización)
│       └── main/resources/
│           ├── application.properties      # Activa perfil dev (spring.profiles.active=dev)
│           └── application-dev.properties  # Configuración PostgreSQL local (puerto 8081)
└── compose-service/                    # Microservicio Compuesto (Reactivo WebClient)
    ├── pom.xml
    └── src/
        ├── main/java/com/cmp_parcel/
        │   ├── config/                 # WebClientConfig (Timeout y ReactorClient)
        │   ├── controller/             # ParcelComposeController (/parcel)
        │   ├── dto/                    # ParcelDto
        │   ├── exception/              # GlobalComposeExceptionHandler
        │   └── service/                # ParcelComposeService (WebClient hacia base-service)
        └── main/resources/
            ├── application.properties      # Activa perfil dev (spring.profiles.active=dev)
            └── application-dev.properties  # Configuración y URL base (puerto 9090)
```

---

## 1. Microservicio Base (`base-service`) - Puerto `8081`

Se encarga de la persistencia directa en PostgreSQL (`parcel_db`), validaciones de entrada, sanitización de cadenas y excepciones personalizadas.

### Endpoints
* `POST /api/v1/parcels`: Registra una nueva parcela aplicando validación y sanitización XSS.
* `GET /api/v1/parcels`: Lista todas las parcelas registradas.
* `GET /api/v1/parcels/{id}`: Consulta una parcela por ID (retorna 404 personalizado si no existe).

---

## 2. Microservicio Compuesto (`compose-service`) - Puerto `9090`

Siguiendo el patrón reactivo de `cmp_student`, utiliza **Spring WebFlux / WebClient** para orquestar y consumir los endpoints de `base-service`.

### Endpoints
* `GET /parcel`: Obtiene todas las parcelas a través de un flujo reactivo (`Flux<ParcelDto>`).
* `GET /parcel/{id}`: Obtiene el detalle de una parcela por ID (`Mono<ParcelDto>`).
* `POST /parcel`: Envía el registro hacia el microservicio base (`Mono<ParcelDto>`).

---

## Buenas Prácticas de Configuración (Perfiles)
Ambos microservicios cuentan con:
- `application.properties`: Define el perfil activo (`spring.profiles.active=dev`).
- `application-dev.properties`: Contiene las propiedades de conexión y puertos para el entorno local.

---

## Instrucciones para Levantar los Servicios

### 1. Iniciar el Microservicio Base
En una primera terminal:
```bash
cd base-service
mvn clean spring-boot:run
```
*Disponible en: `http://localhost:8081`*

### 2. Iniciar el Microservicio Compuesto
En una segunda terminal:
```bash
cd compose-service
mvn clean spring-boot:run
```
*Disponible en: `http://localhost:9090`*
