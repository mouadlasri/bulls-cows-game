import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lengthSecret = 0;
        StringBuilder secret = new StringBuilder();

        do {
            System.out.print("Enter a new number: ");
            lengthSecret = sc.nextInt();

            if(lengthSecret <= 10) {
                break;
            }

            System.out.println("Error: can't generate a secret number with a length of " + lengthSecret + " because there aren't enough unique digits.");
            System.out.println();
        } while(lengthSecret > 10);

        // iterate through the random number from right to left, and add it to our secret code as long as it's unique (0 to 9) and doesn't start with 0
        while (secret.length() != lengthSecret){
            // generate a random number (using nanoTime for example)
            long randomNumber = System.nanoTime();
            String randomNumberStr = String.valueOf(randomNumber);

            // iterate through the String version of randomNumber from right to left
            for (int i = 0; i < randomNumberStr.length(); i++) {
                // our secret code is made and done, exist condition
                if (secret.length() == lengthSecret) {
                    break;
                }

                // converting String ot int (right to left)
                int number = Character.getNumericValue(randomNumberStr.charAt(randomNumberStr.length() - 1 - i));
                if (secret.isEmpty() && number == 0) {
                    // skip it
                    continue;
                } else {
                    // check if it's unique

                    // convert the number into a String and check if it already exists
                    if (!secret.toString().contains(String.valueOf(number))){
                        // add number to our secret random number
                        secret.append(number);
                    }
                }
            }
        }

        System.out.println("The random secret number is " + secret);

    }

    public void grader() {
        char[] secret = {'9', '3', '0', '4'};
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your number: ");
        String input = sc.nextLine();

        char[] inputChar = input.toCharArray();

        int bulls = 0;
        int cows = 0;


        for (int i = 0; i < secret.length; i++) {
            for (int j = 0; j < inputChar.length; j++) {
                if (secret[i] == inputChar[j]) {
                    if (i == j) {
                        // found a bull
                        bulls += 1;
                        break;
                    } else {
                        // found a cow
                        cows += 1;
                        break;
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

        String secretCodeMessage = "The secret code is " + String.valueOf(secret);
        String gradeMessage = "Grade: " + grade;

        System.out.println(gradeMessage + secretCodeMessage);
    }
}
