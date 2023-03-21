package bullscows;

import java.util.Scanner;

public class TextUI {

    Scanner scanner;

    public TextUI() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        play();
    }

    private void play() {

        Game game = setupGame();
        System.out.println(printSecretInfo(game));

        System.out.println("Okay, let's start a game!");

        int turn = 1;
        boolean finished = false;

        while(!finished) {
            System.out.println("Turn " + turn + ":");
            String guess = scanner.nextLine();
            String grade = game.grade(guess);
            System.out.println(grade);

            if (grade.contains(game.getLengthSecret() + " bull")) {
                System.out.println("Congratulations! You guessed the secret code.");
                finished = true;
            }

            turn++;
        }


    }

    private String printSecretInfo(Game game) {

        StringBuilder secretInfo = new StringBuilder();
        secretInfo.append("The secret is prepared: ");

        for (int i = 0; i < game.lengthSecret; i++) {
            secretInfo.append("*");
        }

        int numberOfSymbolsUsed = game.getNumberOfSymbolsUsed();

        secretInfo.append(" (0-");
        if (numberOfSymbolsUsed > 10) {
            secretInfo.append("9, a-" + game.getLastSymbol() );
        } else {
            secretInfo.append(numberOfSymbolsUsed - 1);
        }

        secretInfo.append(").");
        return secretInfo.toString();

    }

    private Game setupGame() {
        System.out.println("Input the length of the secret code:");
        String lengthSecret = scanner.nextLine();
        for (int i = 0; i < lengthSecret.length(); i++) {
            if (!"0123456789".contains(String.valueOf(lengthSecret.charAt(i)))) {
                System.out.println("Error: \"" + lengthSecret + "\" isn't a valid number.");
                System.exit(0);
            }
        }

        System.out.println("Input the number of possible symbols in the code:");
        String numberOfSymbolsUsed = scanner.nextLine();

        for (int i = 0; i < numberOfSymbolsUsed.length(); i++) {
            if (!"0123456789".contains(String.valueOf(numberOfSymbolsUsed.charAt(i)))) {
                System.out.println("Error: \"" + numberOfSymbolsUsed + "\" isn't a valid number.");
                System.exit(0);
            }
        }

        Game game = new Game(Integer.valueOf(lengthSecret), Integer.valueOf(numberOfSymbolsUsed));

        return game;
    }


}
