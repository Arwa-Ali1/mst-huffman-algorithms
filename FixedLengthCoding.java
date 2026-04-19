package g22_Code;

import java.util.*;

// Implements baseline fixed-length encoding: 8 bits per character
public class FixedLengthCoding {

    private Map<Character, String> fixedCodes = new HashMap<>();

    // Assigns 8-bit binary representation for each character
    public void generateCodes(Set<Character> characters) {
        int bits = 8;
        int index = 0;

        for (char c : characters) {
            String binary = String.format("%" + bits + "s", Integer.toBinaryString(index))
                    .replace(' ', '0');
            fixedCodes.put(c, binary);
            index++;
        }
    }

    // Encode entire text using predefined 8-bit codes
    public String encode(String text) {
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(fixedCodes.get(c));
        }
        return encoded.toString();
    }

    // Decode bit string back into original text
    public String decode(String encodedText, int length) {
        StringBuilder decoded = new StringBuilder();

        for (int i = 0; i < encodedText.length(); i += 8) {
            String code = encodedText.substring(i, i + 8);
            for (char key : fixedCodes.keySet()) {
                if (fixedCodes.get(key).equals(code)) {
                    decoded.append(key);
                    break;
                }
            }
        }
        return decoded.toString();
    }

    public Map<Character, String> getCodes() {
        return fixedCodes;
    }
}

