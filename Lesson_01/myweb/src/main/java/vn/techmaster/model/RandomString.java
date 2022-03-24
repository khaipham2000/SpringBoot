package vn.techmaster.model;

import java.util.Random;

public class RandomString {
    private static final String alpha = "abcdefghijklmnopqrstuvwxyz";
    private static final String alphaUpperCase = alpha.toUpperCase();
    private static final String digits = "0123456789";
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    private static Random generator = new Random();
    public static String randomAlphaNumer(int numberOfChar) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfChar; i++) {
            int number = generator.nextInt(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
}
