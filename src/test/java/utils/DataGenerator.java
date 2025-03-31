package utils;

import java.util.Random;

/**
 * Утилита для генерации тестовых данных (Post Code и First Name).
 */
public class DataGenerator {
    private static final Random random = new Random();

    /**
     * Генерирует случайный 10-значный Post Code.
     * @return Строка из 10 цифр (например, "1234567890").
     */
    public static String generatePostCode() {
        long number = Math.abs(random.nextLong() % 10_000_000_000L);
        return String.format("%010d", number);
    }

    /**
     * Преобразует Post Code в имя по заданным правилам.
     * @param postCode 10-значный Post Code.
     * @return Имя из 5 букв английского алфавита.
     * @throws IllegalArgumentException Если Post Code некорректен.
     */
    public static String generateNameFromPostCode(String postCode) {
        if (postCode == null || postCode.length() != 10 || !postCode.matches("\\d+")) {
            throw new IllegalArgumentException("Post Code должен содержать 10 цифр");
        }

        StringBuilder name = new StringBuilder();
        for (int i = 0; i < postCode.length(); i += 2) {
            String pair = postCode.substring(i, i + 2);
            int num = Integer.parseInt(pair);
            char letter = (char) ('a' + (num % 26));
            name.append(letter);
        }
        return name.toString();
    }

    /**
     * Генерирует случайную фамилию.
     * @return Фамилия в формате "UserXXXX" (XXXX — случайное число).
     */
    public static String generateLastName() {
        return "User" + random.nextInt(1000);
    }
}
