import java.util.Random;

public class CodeGenerator {
    private StringBuilder secretCode;
    private final int length;
    private final int numberSymbols;

    public CodeGenerator(int length, int numberSymbols) {
        this.secretCode = new StringBuilder();
        this.length = length;
        this.numberSymbols = numberSymbols;
    }

    private String generateCode() {
        int[] possibleCharacters = this.getPossibleCharactersList();

        Random rand = new Random();
        int lower = 0;
        int higher = possibleCharacters.length - 1;

        // construct the secret code based on the conditions (can only contain unique symbols from the possible characters and doesn't start with a 0)
        while(this.secretCode.length() != this.length) {
            // generate a random number in the range of 0 to size of possibleCharacters
            int randomNumber = rand.nextInt((higher - lower + 1) + lower);

            // break condition
            if (this.secretCode.length() == this.length) {
                break;
            }

            if(this.secretCode.isEmpty() && randomNumber == 0) {
                continue; // skip if the first number is 0 and secret is still empty (code can't start with 0)
            }


            char currentChar;

            if (possibleCharacters[randomNumber] > 10) {
                // get the char of the ascii code (letters)
                currentChar = (char) possibleCharacters[randomNumber];
            } else {
                // get the number as a character (and not the ascii representation of it)
                currentChar = Character.forDigit(possibleCharacters[randomNumber], 10);
            }

            // check if symbol doesn't exist in the secret code already, and add it (secret code has to have unique numbers)
            if (!this.secretCode.toString().contains(String.valueOf(currentChar))) {
                this.secretCode.append(currentChar);
            }
        }

        // finally, replace the ascii code of letters to characters

        // return secret code
        return String.valueOf(secretCode);
    }

    // Construct an array of all possible symbols, store letters as their ascii code
    private int[] getPossibleCharactersList() {
        int[] possibleCharacters = new int[numberSymbols];
        boolean lettersPresent = (numberSymbols - 10) > 0 ? true : false;

        for (int i = 0; i < numberSymbols; i++) {
            if (i >= 10) {
                possibleCharacters[i] = 97 + i - 10; // ascii value
            } else {
                possibleCharacters[i] = i;
            }
        }

//        for (int i = 0; i < possibleCharacters.length; i++) {
//            System.out.print(possibleCharacters[i] + " ");
//        }

        return possibleCharacters;
    }

    public String getSecretCode() {
        return this.generateCode();
    }

    public String getPreparationMessage() {
        boolean lettersPresent = (numberSymbols - 10) > 0 ? true : false;
        int numberLetters = numberSymbols - 10;
        StringBuilder prepMessage = new StringBuilder();

        for (int i = 0; i < length; i++) {
            prepMessage.append("*");
        }

        if (lettersPresent) {
            prepMessage.append(" (0-9, ");
            prepMessage.append((char) 97 + "-" + (char) (97 + numberLetters -1) + ").");
        } else {
            prepMessage.append("(0-" + (numberSymbols - 1) + ")");
        }

        return prepMessage.toString();
    }
}
