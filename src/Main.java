import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String secretCode = newCodeGenerator();
        System.out.println("The secret code is " + secretCode);
        playGame(secretCode);
    }

    public static String newCodeGenerator() {
        Scanner sc = new Scanner(System.in);

        int lengthCode = 0;
        StringBuilder secret = new StringBuilder();

        do {
            System.out.print("Enter a new number: ");
            lengthCode = sc.nextInt();

            if(lengthCode <= 10) {
                break;
            }

            System.out.println("Error: can't generate a secret number with a length of " + lengthCode + " because there aren't enough unique digits.");
            System.out.println();
        } while(lengthCode > 10);

        // iterate through the code length entered by the user (right to left)
        while(secret.length() != lengthCode) {
            // generate a random number (using nanoTime for example)
            long randomNumber = System.nanoTime();
            String randomNumberStr = String.valueOf(randomNumber);

            for (int i = randomNumberStr.length() - 1; i >= 0; i--) {
                // break condition
                if (secret.length() == lengthCode) {
                    break;
                }

                int number = Character.getNumericValue(randomNumberStr.charAt(i));

                if(secret.isEmpty() && number == 0) {
                    continue; // skip if the first number is 0 and secret is still empty (code can't start with 0)
                }

                // check if number doesn't exist in the secret code already, and add it (secret code has to have unique numbers)
                if (!secret.toString().contains(String.valueOf(number))) {
                    secret.append(number);
                }
            }
        }

        // return secret code
        return secret.toString();
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
}
