package g22_Code;

import java.util.*;

public class NewHuffmanCoding {
    public static void main(String[] args) throws Exception {

        String[] files = {"file_50KB.txt", "file_200KB.txt", "file_1MB.txt"};
        int[] sizes = {50, 200, 1024}; // Sizes in KB

        List<PerformanceRecord> results = new ArrayList<>();

        // Generate random test files
        for (int i = 0; i < files.length; i++) {
            RandomTextFileGenerator.generateRandomFile(files[i], sizes[i]);
        }

        for (String file : files) {
            String text = FileUtils.readFile(file);

            // ================= Huffman Encoding =================
            HuffmanCoding huff = new HuffmanCoding();
            long start = System.currentTimeMillis();

            Map<Character, Integer> freq = huff.calculateFrequency(text);
            huff.buildTree(freq);
            
            //Print the code for each Char 
            System.out.println("\nHuffman codes for file: " + file);
            System.out.println("Char\tCode");
            for (Map.Entry<Character, String> entry : huff.getCodes().entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
            }
            System.out.println();

            String encoded = huff.encode(text);

            long encodeTime = System.currentTimeMillis() - start;

            start = System.currentTimeMillis();
            String decoded = huff.decode(encoded);
            long decodeTime = System.currentTimeMillis() - start;

            // Ensure decoded text is correct
            if (!decoded.equals(text)) {
                System.out.println("Huffman Decoding Error!");
            }

            results.add(new PerformanceRecord(
                    file, "Huffman",
                    text.length(),
                    encoded.length() / 8,
                    encodeTime,
                    decodeTime
            ));

            // ================= Fixed-Length Encoding =================
            FixedLengthCoding fixed = new FixedLengthCoding();
            fixed.generateCodes(freq.keySet());

            start = System.currentTimeMillis();
            String fixedEncoded = fixed.encode(text);
            encodeTime = System.currentTimeMillis() - start;

            start = System.currentTimeMillis();
            String fixedDecoded = fixed.decode(fixedEncoded, text.length());
            decodeTime = System.currentTimeMillis() - start;

            if (!fixedDecoded.equals(text)) {
                System.out.println("Fixed-Length Decoding Error!");
            }

            results.add(new PerformanceRecord(
                    file, "Fixed-Length",
                    text.length(),
                    fixedEncoded.length() / 8,
                    encodeTime,
                    decodeTime
            ));
        }

        
        printResultsToConsoleAndFile(results, "performance_results.txt");
    }
    // Prints performance results to both console and output file
    public static void printResultsToConsoleAndFile(List<PerformanceRecord> results, String fileName) throws Exception {
    
        StringBuilder sb = new StringBuilder();

        String header = String.format("%-15s %-15s %-20s %-20s %-20s %-15s %-15s%n",
                "File", "Method", "Original Size", "Compressed Size", "Compression Ratio", "Encode (ms)", "Decode (ms)");

        String line = "----------------------------------------------------------------------------------------------------------------------\n";

        // Append header to StringBuilder
        sb.append(header);
        sb.append(line);

        // Print header to console
        System.out.print(header);
        System.out.print(line);

        // Print and record rows
        for (PerformanceRecord r : results) {
            String row = String.format("%-15s %-15s %-20d %-20d %-20.3f %-15d %-15d%n",
                    r.fileName, r.method, r.originalSize, r.compressedSize, r.compressionRatio, r.encodeTime, r.decodeTime);

            System.out.print(row);
            sb.append(row);
        }

        sb.append(line);
        System.out.print(line);

        // Save to file
        FileUtils.saveFile(fileName, sb.toString());
        System.out.println("\nResults Saved to: " + fileName);
    }

}
