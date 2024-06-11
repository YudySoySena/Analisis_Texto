import java.io.*;
import java.util.*;

public class AnalizadorTexto {

    public static void main(String[] args) {
        String archivoNombre = "ejercicio_java"; // Ajustar nombre del archivo

        try {
            // Lectura del archivo
            String contenido = leerArchivo(archivoNombre);

            // Procesamiento del texto
            Map<String, Integer> contadorPalabras = contarPalabras(contenido);
            int numeroOraciones = contarOraciones(contenido);
            String palabraMasFrecuente = encontrarPalabraMasFrecuente(contadorPalabras);
            double longitudPromedioPalabras = calcularLongitudPromedioPalabras(contadorPalabras);

            // Muestra de resultados
            System.out.println("**Análisis de texto:** " + archivoNombre);
            System.out.println("Número total de palabras: " + obtenerTotalPalabras(contadorPalabras));
            System.out.println("Número total de oraciones: " + numeroOraciones);
            System.out.println("Palabra más frecuente: " + palabraMasFrecuente);
            System.out.println("Longitud promedio de palabras: " + String.format("%.2f", longitudPromedioPalabras));

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private static String leerArchivo(String archivoNombre) throws IOException {
        StringBuilder contenido = new StringBuilder();

        try (FileReader lector = new FileReader(archivoNombre)) {
            BufferedReader lectorBufferizado = new BufferedReader(lector);
            String linea;

            while ((linea = lectorBufferizado.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        }

        return contenido.toString();
    }

    private static Map<String, Integer> contarPalabras(String contenido) {
        Map<String, Integer> contador = new HashMap<>();

        // Separar palabras por espacios y signos de puntuación
        String[] palabras = contenido.split("[ ,.;!?\n]");

        for (String palabra : palabras) {
            palabra = palabra.trim().toLowerCase(); // Eliminar espacios y convertir a minúsculas

            if (!palabra.isEmpty()) {
                int conteo = contador.getOrDefault(palabra, 0);
                contador.put(palabra, conteo + 1);
            }
        }

        return contador;
    }

    private static int contarOraciones(String contenido) {
        int contador = 0;

        for (char caracter : contenido.toCharArray()) {
            if (caracter == '.' || caracter == '?' || caracter == '!') {
                contador++;
            }
        }

        return contador;
    }

    private static String encontrarPalabraMasFrecuente(Map<String, Integer> contadorPalabras) {
        String palabraMasFrecuente = "";
        int frecuenciaMaxima = 0;

        for (Map.Entry<String, Integer> entrada : contadorPalabras.entrySet()) {
            int frecuenciaActual = entrada.getValue();

            if (frecuenciaActual > frecuenciaMaxima) {
                frecuenciaMaxima = frecuenciaActual;
                palabraMasFrecuente = entrada.getKey();
            }
        }

        return palabraMasFrecuente;
    }

    private static double calcularLongitudPromedioPalabras(Map<String, Integer> contadorPalabras) {
        int totalPalabras = obtenerTotalPalabras(contadorPalabras);
        int longitudTotal = 0;

        for (Map.Entry<String, Integer> entrada : contadorPalabras.entrySet()) {
            String palabra = entrada.getKey();
            int frecuencia = entrada.getValue();

            longitudTotal += palabra.length() * frecuencia;
        }

        return (double) longitudTotal / totalPalabras;
    }

    private static int obtenerTotalPalabras(Map<String, Integer> contadorPalabras) {
        int total = 0;

        for (Integer frecuencia : contadorPalabras.values()) {
            total += frecuencia;
        }

        return total;
    }
}