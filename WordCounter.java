import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter '1' to input text, or '2' to provide a file to count words: ");
        int choice = scanner.nextInt();

        String inputText = "";

        if (choice == 1) {
            System.out.println("Enter the text to count words:");
            scanner.nextLine();
            inputText = scanner.nextLine();
        } else if (choice == 2) {
            System.out.println("Enter the path to the file to count words:");
            String filePath = scanner.next();
            try {
                inputText = readTextFromFile(filePath);
            } catch (IOException e) {
                System.err.println("An error occurred while reading the file: " + e.getMessage());
                return;
            }
        } else {
            System.out.println("Invalid choice. Please enter '1' or '2'.");
            return;
        }
        String[] words = inputText.split("[\\s.,!?;:]+");
        int wordCount = words.length;
        System.out.println("Total word count: " + wordCount);
    }
    private static String readTextFromFile(String filePath) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append(" ");
            }
        }
        return text.toString();
    }
}