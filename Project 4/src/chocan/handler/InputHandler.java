package chocan.handler;

/**
 * The InputHandler class provides methods for prompting and handling user input for integers and strings.
 * It includes both constrained and unconstrained prompts for both data types.
 */
import java.util.Scanner;

public class InputHandler {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Constructs an InputHandler object with a Scanner for user input.
     */
    public InputHandler() {
    }

    /**
     * Prompts the user to enter an integer within a specified range.
     *
     * @param prompt the message to display to the user
     * @param min    the minimum allowed value (inclusive)
     * @param max    the maximum allowed value (inclusive)
     * @return the valid integer entered by the user
     */
    public int constrainedPromptInt(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                String userInput = scanner.nextLine();
                int intValue = Integer.parseInt(userInput);

                if (isValid(intValue, min, max)) {
                    return intValue;
                } else {
                    System.out.println("Error: Please enter an integer within the specified range.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer.");
            }
        }
    }

    /**
     * Prompts the user to enter any string.
     *
     * @param prompt the message to display to the user
     * @return the string entered by the user
     */
    public String unconstrainedPromptStr(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Prompts the user to enter a string from a specific list of valid values.
     *
     * @param prompt      the message to display to the user
     * @param validValues an array of valid string values
     * @return the valid string entered by the user
     */
    public String constrainedPromptStr(String prompt, String[] validValues) {
        while (true) {
            System.out.print(prompt);
            String userInput = scanner.nextLine();

            if (isValidString(userInput, validValues)) {
                return userInput;
            } else {
                System.out.println("Error: Please enter a valid value from the given options.");
            }
        }
    }

    /**
     * Prompts the user to enter any integer.
     *
     * @param prompt the message to display to the user
     * @return the integer entered by the user
     */
    public int unconstrainedPromptInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String userInput = scanner.nextLine();
                return Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer.");
            }
        }
    }

    /**
     * Checks if an integer is within a specified range.
     *
     * @param value the integer to check
     * @param min   the minimum allowed value (inclusive)
     * @param max   the maximum allowed value (inclusive)
     * @return true if the integer is within the specified range, false otherwise
     */
    private boolean isValid(int value, int min, int max) {
        return value >= min && value <= max;
    }

    /**
     * Checks if a string is one of the valid values.
     *
     * @param value       the string to check
     * @param validValues an array of valid string values
     * @return true if the string is a valid value, false otherwise
     */
    private boolean isValidString(String value, String[] validValues) {
        for (String validValue : validValues) {
            if (validValue.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * The main method demonstrates the usage of the InputHandler class.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();

        // Examples of using the InputHandler methods
        int constrainedInteger = inputHandler.constrainedPromptInt("Enter an integer between 1 and 100: ", 1, 100);
        System.out.println("You entered: " + constrainedInteger);

        // Do an unconstrained prompt for an integer
        int unconstrainedInteger = inputHandler.unconstrainedPromptInt("Enter any integer: ");
        System.out.println("You entered: " + unconstrainedInteger);

        // Do an unconstrained prompt for a string
        String unconstrainedString = inputHandler.unconstrainedPromptStr("Enter any string: ");
        System.out.println("You entered: " + unconstrainedString);


    }
}
