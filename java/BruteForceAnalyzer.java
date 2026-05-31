public class BruteForceAnalyzer {

    public static void analyze(String encryptedText) {
        System.out.println("\n🔍 Brute Force анализ (все возможные сдвиги):\n");
        for (int shift = 0; shift < 26; shift++) {
            String decrypted = CaesarCipher.decrypt(encryptedText, shift);
            System.out.printf("Сдвиг %2d: %s%n", shift, decrypted.substring(0,
                    Math.min(80, decrypted.length())));
        }
        System.out.println("\nПросмотрите результаты и выберите наиболее читаемый текст.");
    }
}
