import java.util.Scanner;
import java.util.Random;
public class NumberGame{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int lBound = 1;
        int uBound = 100;
        int num = random.nextInt(uBound - lBound + 1) + lBound;
        int attempts = 0;
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("There is a random number between 1 and 100. Try to guess it.");
        int guess;
        boolean guessedCorrectly = false;
        while (!guessedCorrectly) {
            System.out.print("Your guess: ");
            guess = scanner.nextInt();
            attempts++;
            if (guess < num) {
                System.out.println("Too low! Try again.");
            } else if (guess > num) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You guessed the correct number: " + num);
                guessedCorrectly = true;
            }
        }
        System.out.println("Number of attempts: " + attempts);
        System.out.println("Thanks for playing!");
    }
}
