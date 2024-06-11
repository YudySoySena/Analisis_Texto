import java.io.BufferedReader;  
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TextAnalysis {
    public static void main(String[] args) {
        // Ruta del archivo de texto
        String filePath = "C:\\Users\\Aprendiz\\Downloads\\ejercicio_java (2)\\ejercicio_java\\analisis de texto\\src\\main\\java\\archivo.txt";

        // Variables para almacenar las estadísticas
        int wordCount = 0;
        int sentenceCount = 0;
        int totalWordLength = 0;
        Map<String, Integer> wordFrequency = new HashMap<>();

        // Leer el archivo de texto y procesar su contenido
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Dividir la línea en palabras
                String[] words = line.split("\\s+");
                wordCount += words.length;

                for (String word : words) {
                    // Eliminar puntuación y convertir a minúsculas
                    word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

                    // Incrementar el conteo de palabras
                    if (!word.isEmpty()) {
                        totalWordLength += word.length();
                        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                    }
                }

                // Contar oraciones (asumiendo que terminan con ., ! o ?)
                String[] sentences = line.split("[.!?]");
                sentenceCount += sentences.length;
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        // Determinar la palabra más frecuente
        String mostFrequentWord = null;
        int maxFrequency = 0;
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mostFrequentWord = entry.getKey();
            }
        }

        // Calcular la longitud promedio de las palabras
        double averageWordLength = wordCount == 0 ? 0 : (double) totalWordLength / wordCount;

        // Mostrar los resultados
        System.out.println("Número total de palabras: " + wordCount);
        System.out.println("Número total de oraciones: " + sentenceCount);
        System.out.println("Palabra más frecuente: " + (mostFrequentWord != null ? mostFrequentWord : "N/A"));
        System.out.println("Longitud promedio de las palabras: " + averageWordLength);
    }
}
