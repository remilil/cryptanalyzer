import java.util.Scanner;

public class ConsoleMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Caesar Cipher Tool ===");
        System.out.println("JDK 21+ | Поддержка больших файлов\n");

        while (true) {
            printMenu();
            int choice = getIntInput(scanner, "Выберите действие: ");

            try {
                switch (choice) {
                    case 1 -> encryptMode(scanner);
                    case 2 -> decryptWithKeyMode(scanner);
                    case 3 -> bruteForceMode(scanner);
                    case 4 -> statisticalMode(scanner);
                    case 5 -> {
                        System.out.println("👋 Выход из программы.");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("❌ Неверный выбор!");
                }
            } catch (Exception e) {
                System.out.println("❌ Ошибка: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("1. Зашифровать текст");
        System.out.println("2. Расшифровать с известным ключом");
        System.out.println("3. Brute Force (перебор)");
        System.out.println("4. Статистический анализ");
        System.out.println("5. Выход");
        System.out.println("=".repeat(40));
    }

    private static void encryptMode(Scanner sc) throws Exception {
        String input = getInput(sc, "Введите текст или путь к файлу: ");
        int shift = getIntInput(sc, "Введите сдвиг (ключ): ");

        String text = input.contains(".") && !input.contains(" ") ? FileReader.readFile(input) : input;
        String encrypted = CaesarCipher.encrypt(text, shift);

        System.out.println("\n🔐 Зашифрованный текст:\n" + encrypted);
        saveToFile(sc, encrypted);
    }

    private static void decryptWithKeyMode(Scanner sc) throws Exception {
        String input = getInput(sc, "Введите зашифрованный текст или путь к файлу: ");
        int shift = getIntInput(sc, "Введите ключ (сдвиг): ");

        String text = input.contains(".") && !input.contains(" ") ? FileReader.readFile(input) : input;
        String decrypted = CaesarCipher.decrypt(text, shift);

        System.out.println("\n🔓 Расшифрованный текст:\n" + decrypted);
        saveToFile(sc, decrypted);
    }

    private static void bruteForceMode(Scanner sc) throws Exception {
        String input = getInput(sc, "Введите зашифрованный текст или путь к файлу: ");
        String text = input.contains(".") && !input.contains(" ") ? FileReader.readFile(input) : input;
        BruteForceAnalyzer.analyze(text);
    }

    private static void statisticalMode(Scanner sc) throws Exception {
        String input = getInput(sc, "Введите зашифрованный текст или путь к файлу: ");
        String text = input.contains(".") && !input.contains(" ") ? FileReader.readFile(input) : input;
        StatisticalAnalyzer.analyze(text);
    }

    private static void saveToFile(Scanner sc, String content) throws Exception {
        System.out.print("Сохранить в файл? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) {
            String path = getInput(sc, "Введите имя файла (например: result.txt): ");
            FileWriter.writeFile(content, path);
        }
    }

    private static String getInput(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static int getIntInput(Scanner sc, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Введите число!");
            }
        }
    }
}