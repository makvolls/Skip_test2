package Utils.generator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DataGenerator {
    public static String randomNumber(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += new Random().nextInt(10);
        }
        return result;
    }

    public static String randomString(String characters, int length) {
        Random random = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; ++i)
            text[i] = characters.charAt(random.nextInt(characters.length()));
        return new String(text);
    }

    public static String randomRusString(int length) {
        String characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        return randomString(characters, length);
    }

    public static String randomLatString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        return randomString(characters, length);
    }

    public static String name() {
        return "Тест-" + randomRusString(5);
    }

    public static String latName() {
        return "Test-" + randomLatString(5);
    }

    public static String seriesDoc() {
        return randomNumber(4);
    }

    public static String numberDoc() {
        return randomNumber(6);
    }

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static LocalDate currentTime() {
        return LocalDate.now();
    }

    public static String  currentDate() {
        return currentTime().format(dateTimeFormatter);
    }

    public static String minusDay(int dayCount) {
        return currentTime().minusDays(dayCount).format(dateTimeFormatter);
    }
    public static String minusDay() {
        return minusDay(1);
    }

    public static String plusDay(int dayCount) {
        return currentTime().plusDays(dayCount).format(dateTimeFormatter);
    }
    public static String plusDay() {
        return plusDay(1);
    }

    public static String minusMonth(int monthCount) {
        return currentTime().minusMonths(monthCount).format(dateTimeFormatter);
    }
    public static String plusMonth(int monthCount) {
        return currentTime().plusMonths(monthCount).format(dateTimeFormatter);
    }

    public static String minusMonth() {
        return minusMonth(1);
    }
    public static String plusMonth() {
        return plusMonth(1);
    }


}
