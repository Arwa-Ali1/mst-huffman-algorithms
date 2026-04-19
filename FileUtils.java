package g22_Code;

import java.io.*;
import java.nio.file.*;


// Utility class for file input/output operations
public class FileUtils {

    // Saves content into a file with given name
    public static void saveFile(String fileName, String content) throws IOException {
        Files.write(Paths.get(fileName), content.getBytes());
    }
    // Reads entire file as a String
    public static String readFile(String fileName) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(fileName));
    return new String(bytes);
    }

    // Returns file size in bytes
    public static long getFileSize(String fileName) throws IOException {
    return Files.size(Paths.get(fileName));
    }
}

