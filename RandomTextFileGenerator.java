package g22_Code;

import java.util.Random;
import java.io.*;

// Generates random text files with A–Z uppercase letters
public class RandomTextFileGenerator {

    public static void generateRandomFile(String fileName, int sizeKB) throws Exception {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        int length = sizeKB * 1024; // Convert KB to bytes

        StringBuilder sb = new StringBuilder(length);

        // Add random characters until file reaches the desired size
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }

        FileUtils.saveFile(fileName, sb.toString());
    }
}