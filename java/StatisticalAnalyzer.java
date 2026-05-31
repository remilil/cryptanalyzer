import java.util.*;

public class StatisticalAnalyzer {

    private static final String RUSSIAN_FREQ = "оеаинтсрвлкмдпуяьыбзжчхшюцщэфъ";

    public static int findBestShift(String text) {
        text = text.toLowerCase().replaceAll("[^а-я]", "");
        if (text.isEmpty()) return 0;

        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> sorted = new ArrayList<>(freq.entrySet());
        sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        if (!sorted.isEmpty()) {
            char mostCommon = sorted.get(0).getKey();
            int mostCommonIndex = RUSSIAN_FREQ.indexOf(mostCommon);
            int expectedIndex = RUSSIAN_FREQ.indexOf('о'); // самая частая буква в русском

            if (mostCommonIndex != -1) {
                return (mostCommonIndex - expectedIndex + 26) % 26;
            }
        }
        return 0;
    }

    public static void analyze(String encryptedText) {
        int bestShift = findBestShift(encryptedText);
        String decrypted = CaesarCipher.decrypt(encryptedText, bestShift);

        System.out.println("\n📊 Статистический анализ:");
        System.out.println("Предполагаемый сдвиг: " + bestShift);
        System.out.println("Расшифрованный текст:\n");
        System.out.println(decrypted.substring(0, Math.min(500, decrypted.length())));
        System.out.println("\n...");
    }
}
