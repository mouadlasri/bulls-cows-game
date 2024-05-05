import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] secret = {'9', '3', '2', '0'};

        System.out.print("Enter your number: ");
        String input = sc.nextLine();
        char[] inputChar = input.toCharArray();

        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < secret.length; i++) {
            for (int j = 0; j <inputChar.length; j++) {
                if (secret[i] == inputChar[j]) {
                    if (i == j) {
                        bulls += 1;
                        break;
                    } else {
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