package com.garden.parcel.util;

import java.util.regex.Pattern;

/**
 * Utilería para sanitización de cadenas de entrada.
 * - Elimina espacios en blanco innecesarios.
 * - Limpia etiquetas HTML/JavaScript para mitigar inyecciones XSS.
 * - Normaliza formato de datos.
 */
public final class SanitizerUtil {

    private static final Pattern HTML_TAG_PATTERN = Pattern.compile("<[^>]*>", Pattern.CASE_INSENSITIVE);
    private static final Pattern MULTI_SPACE_PATTERN = Pattern.compile("\\s+");

    private SanitizerUtil() {
        // Clase de utilería, constructor privado
    }

    /**
     * Sanitiza texto general:
     * 1. Elimina etiquetas HTML/Scripts.
     * 2. Reemplaza múltiples espacios en blanco por uno solo.
     * 3. Aplica trim en extremos.
     */
    public static String sanitizeText(String input) {
        if (input == null) {
            return null;
        }

        // Remover etiquetas HTML/Script
        String clean = HTML_TAG_PATTERN.matcher(input).replaceAll("");

        // Reducir espacios múltiples consecutivos y recortar extremos
        clean = MULTI_SPACE_PATTERN.matcher(clean).replaceAll(" ").trim();

        return clean;
    }

    /**
     * Sanitiza y normaliza coordenadas GPS eliminando espacios alrededor de la coma.
     * Ejemplo: " 19.4326 , -99.1332 " -> "19.4326,-99.1332"
     */
    public static String sanitizeCoordinates(String coordinates) {
        if (coordinates == null) {
            return null;
        }
        return coordinates.replaceAll("\\s+", "").trim();
    }
}
