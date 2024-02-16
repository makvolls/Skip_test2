package Utils;

import java.util.Random;

public class RandomGenerateText {
    public static synchronized String generateUniqeTextRussianString(int length) {
        String result = "";
        Random rnd = new Random();
        String characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        char[] text = new char[length];

        for(int i = 0; i < length; ++i) {
            text[i] = characters.charAt(rnd.nextInt(characters.length()));
        }

        result = new String(text);
        return result;
    }
}
