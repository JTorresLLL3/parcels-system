# Red de Huertos Urbanos Comunitarios - Monorepo Microservicios

Sistema para la gestión y monitoreo de parcelas agrícolas urbanas mediante arquitectura de microservicios con Spring Boot, Spring Data JPA, PostgreSQL y WebClient reactivo.

---

## Estructura del Repositorio (Monorepo)

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
│       │   ├── service/                # ParcelService (Lógica de negocio y sanitización)
│       │   └── util/                   # SanitizerUtil (XSS, normalización de coordenadas)
│       └── main/resources/
│           ├── application.properties      # Activa perfil dev (spring.profiles.active=dev)
│           ├── application-dev.properties  # Configuración PostgreSQL local (puerto 8081)
│           └── schema.sql                  # Script DDL de referencia
└── compose-service/                    # Microservicio Compuesto (Reactivo WebClient)
    ├── pom.xml
    └── src/
        ├── main/java/com/cmp_parcel/
        │   ├── config/                 # WebClientConfig (Timeout y ReactorClient)
        │   ├── controller/             # ParcelComposeController (/parcel)
        │   ├── dto/                    # ParcelDto
        │   └── service/                # ParcelComposeService (WebClient hacia base-service)
        └── main/resources/
            ├── application.properties      # Activa perfil dev (spring.profiles.active=dev)
            └── application-dev.properties  # Configuración y URL base (puerto 9090)
```

---

## Requisitos Previos

* **Java:** 17 o superior (desarrollado y probado en Java 21 LTS).
* **Maven:** 3.8+
* **PostgreSQL:** 14 o superior (corriendo en el puerto por defecto `5432`).

---

## Configuración de la Base de Datos (PostgreSQL)

### 1. Crear la Base de Datos
En tu gestor favorito (**pgAdmin** o consola **psql**), ejecuta una sola vez:

```sql
CREATE DATABASE parcel_db;
```

### 2. Generación Automática de Tablas
> **Nota importante:** El proyecto utiliza `spring.jpa.hibernate.ddl-auto=update`. **No es necesario crear tablas manualmente.**
> En cuanto se inicie el microservicio `base-service`, Hibernate creará automáticamente la tabla `parcels` con sus tipos de datos y restricciones.

*(Opcional: Si el evaluador desea revisar el DDL manual o insertar datos semilla, puede consultar el archivo `base-service/src/main/resources/schema.sql`).*

### 3. Credenciales de Base de Datos
Por defecto, el proyecto está configurado para desarrollo local en `base-service/src/main/resources/application-dev.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/parcel_db
spring.datasource.username=postgres
spring.datasource.password=postgres
```
*Si tu usuario o contraseña local de PostgreSQL son diferentes, únicamente ajústalos en dicho archivo.*

---

## Instrucciones para Levantar los Servicios

### Paso 1: Iniciar el Microservicio Base (`base-service`)
Abre una terminal en la raíz del proyecto:
```bash
cd base-service
mvn spring-boot:run
```
* **Puerto:** `8081`
* **URL Base:** `http://localhost:8081/api/v1/parcels`

### Paso 2: Iniciar el Microservicio Compuesto (`compose-service`)
Abre una segunda terminal en la raíz del proyecto:
```bash
cd compose-service
mvn spring-boot:run
```
* **Puerto:** `9090`
* **URL Base:** `http://localhost:9090/parcel`
* **Mecanismo:** Consume al `base-service` de forma no bloqueante utilizando **Spring WebFlux / WebClient**.

---

## Guía de Endpoints y Pruebas Rápidas

### A. Microservicio Base (`http://localhost:8081`)

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/api/v1/parcels` | Registra una nueva parcela aplicando validación declarativa y sanitización XSS |
| `GET` | `/api/v1/parcels` | Lista todas las parcelas registradas |
| `GET` | `/api/v1/parcels/{id}` | Consulta el detalle de una parcela por ID (retorna 404 personalizado si no existe) |

#### Ejemplo de `POST /api/v1/parcels` (Creación válida):
```json
{
  "name": "Huerto Experimental Norte",
  "location": "19.4326,-99.1332",
  "soilType": "arcilloso",
  "ownerOrLead": "Dra. Elena Ramos"
}
```

#### Ejemplo de Respuesta de Error de Validación (`HTTP 400 Bad Request`):
```json
{
  "timestamp": "2026-10-14T19:00:26Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Error en las validaciones de la solicitud",
  "path": "/api/v1/parcels",
  "errors": [
    { "field": "name", "message": "El nombre de la parcela es obligatorio", "rejectedValue": "" },
    { "field": "location", "message": "La ubicación debe contener coordenadas GPS válidas en formato 'latitud,longitud'", "rejectedValue": "invalida" }
  ]
}
```

---

### B. Microservicio Compuesto (`http://localhost:9090`)

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/parcel` | Obtiene el flujo reactivo de todas las parcelas (`Flux<ParcelDto>`) |
| `GET` | `/parcel/{id}` | Consulta una parcela vía WebClient (`Mono<ParcelDto>`) |
| `POST` | `/parcel` | Crea una parcela orquestando la petición hacia el servicio base |
