package gg.corn.seed2ban;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public class SpecialCharacters {

    //Resolves special characters to their unicode equivalents.

    private static final Map<String, String> numberCharMap = new HashMap<>();

    static {
        // Arabic Numerals (Standard)
        for (int i = 0; i <= 9; i++) {
            numberCharMap.put(String.valueOf(i), String.valueOf(i));
        }

        // letters that look like numbers
        numberCharMap.put("O", "0"); // Capital letter O to zero
        numberCharMap.put("o", "0"); // Lowercase letter o to zero
        numberCharMap.put("I", "1"); // Capital letter I to one
        numberCharMap.put("|", "1"); // | to one
        numberCharMap.put("l", "1"); // Lowercase letter l to one
        numberCharMap.put("Z", "2"); // Capital letter Z to two
        numberCharMap.put("S", "5"); // Capital letter S to five
        numberCharMap.put("s", "5"); // Lowercase letter s to five
        numberCharMap.put("B", "8"); // Capital letter B to eight
        numberCharMap.put("E", "3"); // Capital letter E to three
        numberCharMap.put("Ϭ", "6"); // Ϭ to six
        numberCharMap.put("ꝯ", "9");
        numberCharMap.put("Ꝯ", "9");
        numberCharMap.put("ꝰ", "9");
        numberCharMap.put("ꜭ", "4");
        numberCharMap.put("Ꜭ", "4");
        numberCharMap.put("Ƽ", "5");
        numberCharMap.put("ƽ", "5");
        numberCharMap.put("Ʒ", "3");
        numberCharMap.put("ʒ", "3");
        numberCharMap.put("ᶾ", "3");
        numberCharMap.put("Ꝣ", "3");
        numberCharMap.put("ꝣ", "3");
        numberCharMap.put("Ӡ", "3");
        numberCharMap.put("ϩ", "2");



        // Fullwidth Numerals
        numberCharMap.put("０", "0");
        numberCharMap.put("１", "1");
        numberCharMap.put("２", "2");
        numberCharMap.put("３", "3");
        numberCharMap.put("４", "4");
        numberCharMap.put("５", "5");
        numberCharMap.put("６", "6");
        numberCharMap.put("７", "7");
        numberCharMap.put("８", "8");
        numberCharMap.put("９", "9");

        // Circled Numerals
        numberCharMap.put("①", "1");
        numberCharMap.put("②", "2");
        numberCharMap.put("③", "3");
        numberCharMap.put("④", "4");
        numberCharMap.put("⑤", "5");
        numberCharMap.put("⑥", "6");
        numberCharMap.put("⑦", "7");
        numberCharMap.put("⑧", "8");
        numberCharMap.put("⑨", "9");

        // Double Circled Numerals
        numberCharMap.put("⓪", "0");
        numberCharMap.put("⑩", "10");
        // Note: Double circled 1-9 are not included in Unicode

        // Parenthesized Numerals
        numberCharMap.put("⑴", "1");
        numberCharMap.put("⑵", "2");
        numberCharMap.put("⑶", "3");
        numberCharMap.put("⑷", "4");
        numberCharMap.put("⑸", "5");
        numberCharMap.put("⑹", "6");
        numberCharMap.put("⑺", "7");
        numberCharMap.put("⑻", "8");
        numberCharMap.put("⑼", "9");

        // Mathematical Bold Script Numerals
        numberCharMap.put("𝟙", "1");
        numberCharMap.put("𝟚", "2");
        numberCharMap.put("𝟛", "3");
        numberCharMap.put("𝟜", "4");
        numberCharMap.put("𝟝", "5");
        numberCharMap.put("𝟞", "6");
        numberCharMap.put("𝟟", "7");
        numberCharMap.put("𝟠", "8");
        numberCharMap.put("𝟡", "9");
        numberCharMap.put("𝟘", "0");

        // Inverse circled numbers
        numberCharMap.put("⓿", "0");
        numberCharMap.put("❶", "1");
        numberCharMap.put("❷", "2");
        numberCharMap.put("❸", "3");
        numberCharMap.put("❹", "4");
        numberCharMap.put("❺", "5");
        numberCharMap.put("❻", "6");
        numberCharMap.put("❼", "7");
        numberCharMap.put("❽", "8");
        numberCharMap.put("❾", "9");

        //sans serif dingbat
        numberCharMap.put("➀", "1");
        numberCharMap.put("➁", "2");
        numberCharMap.put("➂", "3");
        numberCharMap.put("➃", "4");
        numberCharMap.put("➄", "5");
        numberCharMap.put("➅", "6");
        numberCharMap.put("➆", "7");
        numberCharMap.put("➇", "8");
        numberCharMap.put("➈", "9");

        // Mathematical Sans-Serif Bold Digits
        numberCharMap.put("𝟬", "0");
        numberCharMap.put("𝟭", "1");
        numberCharMap.put("𝟮", "2");
        numberCharMap.put("𝟯", "3");
        numberCharMap.put("𝟰", "4");
        numberCharMap.put("𝟱", "5");
        numberCharMap.put("𝟲", "6");
        numberCharMap.put("𝟳", "7");
        numberCharMap.put("𝟴", "8");
        numberCharMap.put("𝟵", "9");

        // More Mathematical Sans-Serif Bold Digits
        numberCharMap.put("𝟎", "0");
        numberCharMap.put("𝟏", "1");
        numberCharMap.put("𝟐", "2");
        numberCharMap.put("𝟑", "3");
        numberCharMap.put("𝟒", "4");
        numberCharMap.put("𝟓", "5");
        numberCharMap.put("𝟔", "6");
        numberCharMap.put("𝟕", "7");
        numberCharMap.put("𝟖", "8");
        numberCharMap.put("𝟗", "9");

        // Superscript Numerals
        numberCharMap.put("⁰", "0");
        numberCharMap.put("¹", "1");
        numberCharMap.put("²", "2");
        numberCharMap.put("³", "3");
        numberCharMap.put("⁴", "4");
        numberCharMap.put("⁵", "5");
        numberCharMap.put("⁶", "6");
        numberCharMap.put("⁷", "7");
        numberCharMap.put("⁸", "8");
        numberCharMap.put("⁹", "9");

        // Subscript Numerals
        numberCharMap.put("₀", "0");
        numberCharMap.put("₁", "1");
        numberCharMap.put("₂", "2");
        numberCharMap.put("₃", "3");
        numberCharMap.put("₄", "4");
        numberCharMap.put("₅", "5");
        numberCharMap.put("₆", "6");
        numberCharMap.put("₇", "7");
        numberCharMap.put("₈", "8");
        numberCharMap.put("₉", "9");

    }

    public static String convertNumberChars(String input) {
        String normalizedInput = Normalizer.normalize(input, Normalizer.Form.NFKD);
        StringBuilder convertedString = new StringBuilder();

        for (int i = 0; i < normalizedInput.length(); i++) {
            String charStr = String.valueOf(normalizedInput.charAt(i));

            if (Character.isDigit(normalizedInput.charAt(i))) {
                // Directly append standard digits
                convertedString.append(Character.getNumericValue(normalizedInput.charAt(i)));
            } else {
                // For special characters, look up in the map
                convertedString.append(numberCharMap.getOrDefault(charStr, charStr));
            }
        }

        return convertedString.toString();
    }



}