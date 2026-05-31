public class CaesarCipher {

    private static final String RUSSIAN_UPPER = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String RUSSIAN_LOWER = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static String encrypt(String text, int shift) {
        return processText(text, shift % 33);
    }

    public static String decrypt(String text, int shift) {
        return processText(text, 33 - (shift % 33));
    }

    private static String processText(String text, int shift) {
        if (text == null) return "";

        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c) && RUSSIAN_UPPER.indexOf(c) != -1) {
                // Обработка заглавных русских букв
                int index = RUSSIAN_UPPER.indexOf(c);
                int newIndex = (index + shift) % 33;
                result.append(RUSSIAN_UPPER.charAt(newIndex));
            }
            else if (Character.isLowerCase(c) && RUSSIAN_LOWER.indexOf(c) != -1) {
                // Обработка строчных русских букв
                int index = RUSSIAN_LOWER.indexOf(c);
                int newIndex = (index + shift) % 33;
                result.append(RUSSIAN_LOWER.charAt(newIndex));
            }
            else {
                // Оставляем все остальные символы (пробелы, знаки препинания, английские буквы и т.д.) без изменений
                result.append(c);
            }
        }
        return result.toString();
    }
}