package bullscows;

import java.util.ArrayList;

public class Game {

    String secretCode;
    int lengthSecret;
    int numberOfSymbolsUsed;
    final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyz";

    public Game(int lengthSecret, int numberOfSymbolsUsed) {

        if (lengthSecret > 36 || lengthSecret == 0) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        } else if (lengthSecret > numberOfSymbolsUsed) {
            System.out.println("Error: it's not possible to generate a code with a length of " + lengthSecret + " with " + numberOfSymbolsUsed + " unique symbols.");
            System.exit(0);
        } else if (numberOfSymbolsUsed > alphabet.length()) {
            System.out.println("Error: Only " + alphabet.length() + 1 + " symbols available.");
            System.exit(0);
        }

        else {
            String result = "";

            this.lengthSecret = lengthSecret;
            this.numberOfSymbolsUsed = numberOfSymbolsUsed;

            secretCode = generateSecretCode();

        }

    }


    public String generateSecretCode() {

        StringBuilder code = new StringBuilder();

        while (code.length() < lengthSecret) {

            int n = (int) Math.floor(Math.random() * numberOfSymbolsUsed);
            System.out.println("n: " + n);
            char c = alphabet.charAt(n);
            System.out.println("c: " + c);

            if (!code.toString().contains(String.valueOf(c))) {
                code.append(c);

            }

        }

        return code.toString();
    }

    int getLengthSecret() {
        return lengthSecret;
    }

    int getNumberOfSymbolsUsed() {
        return numberOfSymbolsUsed;
    }

    char getLastSymbol() {
        return alphabet.charAt(numberOfSymbolsUsed - 1);
    }

    public String grade(String guess) {
        StringBuilder result = new StringBuilder("Grade: ");

        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < secretCode.length(); i++) {
            int position = secretCode.indexOf(guess.charAt(i));
            if (i == position) {
                bulls++;
            } else if (position >= 0) {
                cows++;
            }
        }

        if (bulls == 0 && cows == 0) {
            result.append("None");
        } else if (bulls != 0) {
            result.append(bulls + " bull");
            if (bulls > 1) {
                result.append("s");
            }

            if (cows != 0) {
                result.append(" and " + cows + " cow");
                if (cows > 1) {
                    result.append("s");
                }
            }

        } else {
            result.append(cows + " cows");
        }

        result.append(".");

        return result.toString();
    }



}
