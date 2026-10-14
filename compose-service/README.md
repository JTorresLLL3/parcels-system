# Compose Service (Microservicio Compuesto / Orquestador)

Este directorio contendrá el **Microservicio Compuesto**, encargado de orquestar y componer la información del sistema (consumo del `base-service`, alertas IoT o recomendaciones automatizadas para los huertos).

## Estructura Prevista
- Consumo del `base-service` (cliente REST / Feign / WebClient).
- Gestión de recomendaciones basadas en las condiciones y tipos de suelo de las parcelas.
