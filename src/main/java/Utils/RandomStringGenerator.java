package Utils;

import java.util.Random;

public class RandomStringGenerator {
    public RandomStringGenerator(int length){
        // Create object Random class
        Random random = new Random();

        // Create an empty string to which we will add characters
        StringBuilder sb = new StringBuilder();

        // Generate random characters and add it to string
        for (int i = 0; i < length; i++){
            // Get a random character index from the range a-z (ASCII 97-122)
            int randomIndex = random.nextInt(26) + 97;
            // Convert the random index into the character and add it to the string
            sb.append((char) randomIndex);
        }
    }
}