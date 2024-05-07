
import java.util.Scanner;
import java.util.Random;


public class Main {
    public static void main(String[] args) {

        String secretCode = newCodeGenerator();
//        System.out.println("The secret code is " + secretCode); // only for testing purposes
        playGame(secretCode);
    }

    public static String newCodeGenerator() {
        Scanner sc = new Scanner(System.in);

        int lengthCode = 0;
        int lengthPossibleSymbols = 0;
        String input;
        StringBuilder secret = new StringBuilder();

        // Get length of code from user (has to be between 0 and 36, ie: 10 numbers + 26 letters)
        do {
            System.out.print("Input the length of the secret code: ");
            input = sc.nextLine();

            if (isNumber(input)) {
                lengthCode = Integer.parseInt(input);
            } else {
                System.out.println("Error: " + input + " isn't a valid number");
                System.exit(0); // only for the sake of task #7
            }

            if(lengthCode <= 36 && lengthCode > 0) {
                break;
            }


            System.out.println("Error: can't generate a secret number with a length of " + lengthCode + " because there aren't enough unique digits and characters.");
            System.out.println();
            System.exit(0); // only for the sake of task #7
        } while(lengthCode > 36 || lengthCode <= 0);

        // Get number of possible symbols
        do {
            System.out.print("Input the number of possible symbols in the code: ");
            input = sc.nextLine();

            if (isNumber(input)) {
                lengthPossibleSymbols = Integer.parseInt(input);
            } else {
                System.out.println("Error: " + input + " isn't a valid number");
                System.exit(0);
            }

            if (lengthPossibleSymbols <= 36 && lengthPossibleSymbols > 0) {
                break;
            }

            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z)");
            System.out.println();
            System.exit(0); // only for the sake of task #7
        } while(lengthPossibleSymbols > 36 || lengthPossibleSymbols <= 0);

        if (lengthCode > lengthPossibleSymbols) {
            System.out.println("Error: it's not possible to generate a code with a length of " + lengthCode + " with " + lengthPossibleSymbols + " unique symbols.");
            System.exit(0); // only for the sake of task #7
        }

        CodeGenerator codeGenerator = new CodeGenerator(lengthCode, lengthPossibleSymbols);
        System.out.println(codeGenerator.getPreparationMessage());
        return codeGenerator.getSecretCode();
    }

    public static void grader(String secretCode, String userInput) {

        int bulls = 0;
        int cows = 0;

        // create arrays to mark matched positions and avoid checking the same character again
        boolean[] secretMatched = new boolean[secretCode.length()];
        boolean[] guessMatched = new boolean[secretCode.length()];

        // first pass, count bulls
        for (int i = 0; i < secretCode.length(); i++) {
            if (secretCode.charAt(i) == userInput.charAt(i)) {
                secretMatched[i] = true;
                guessMatched[i] = true;
                bulls++;
            }
        }

        // second pass, count cows
        for (int i = 0; i < secretCode.length(); i++) {
            // iterate through the secretMatched positions that are still False
            if (!secretMatched[i]) {
                for (int j = 0; j < userInput.length(); j++) {
                    if (!guessMatched[j] && secretCode.charAt(i) == userInput.charAt(j)) {
                        cows++;
                        guessMatched[j] = true; // mark as matched
                        break; // stop looking for other matches for this character
                    }
                }
            }
        }

        String grade = "";

        if (bulls == 0 && cows == 0) {
            grade = "None.";
        } else if (bulls == 0) {
            grade = cows + " cow(s).";
        } else if (cows == 0) {
            grade = bulls + " bull(s).";
        } else {
            grade = bulls + " bull(s)" + " and " + cows + " cow(s). ";
        }

//        String secretCodeMessage = "The secret code is " + String.valueOf(secret);
        String gradeMessage = "Grade: " + grade;

        System.out.println(gradeMessage);
    }

    public static void playGame(String secretCode) {
        int turnNumber = 0;
        String userInput;

        System.out.println("Okay, let's start a game!");

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Turn " + ++turnNumber + ":");
            userInput = sc.nextLine();

            // Do Later: Check validity of userInput

            grader(secretCode, userInput);
        } while(!userInput.equals(secretCode));

        System.out.println("Congratulations! You guessed the secret code");

    }

    public static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ( c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
