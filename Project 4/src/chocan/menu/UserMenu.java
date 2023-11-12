package chocan.menu;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.HashMap;
import chocan.controller.AbstractController;

/**
 * Abstract class representing a user menu in the ChocAn system.
 */
public abstract class UserMenu extends AbstractMenu {

    /**
     * Flag to exit the menu.
     */
    protected boolean exitFlag;

    /*
     * Option HashMaps for the menus.
     */
    protected HashMap<Integer, String> options;

    /**
     * Constructs a UserMenu with the specified controller.
     *
     * @param controller AbstractController, the controller for this menu.
     */
    public UserMenu(AbstractController controller) {
        super(controller);

        // Initialize options HashMap
        this.options = getOptions();

        // Union Exit option to the greatest key + 1 (to avoid collisions)
        int exitOption = options.keySet().stream().max(Integer::compare).get() + 1;

        // Add exit option to the greatest key + 1 (to avoid collisions)
        options.put(exitOption, "Exit");
        
    }


    /**
     * Gets the menu options for this menu.
     *
     * @return HashMap<Integer, String>, the menu options for this menu.
     */
    protected abstract HashMap<Integer, String> getOptions();

    
    /**
     * Displays the menu options to the user.
     */
    public void displayMenu() {
        // Print the menu options
        printOptionTable(options);
    }

    /**
     * Executes the user-selected option from the menu.
     *
     * @param option int, the option chosen by the user.
     */
    protected abstract void chooseOption(int option);


    /**
     * Called when the user wants to exit the menu.
     */
    protected void exit(){
        // Set the exit flag to true
        this.exitFlag = true;
    }

    /**
     * Checks if the given option is valid.
     *
     * @param option int, the option chosen by the user.
     * @return boolean, true if the option is valid, false otherwise.
     */
    public boolean isValidOption(int option){
        return options.containsKey(option); // Check against the options HashMap
    }

    /**
     * Reads the next string from the provided scanner.
     *
     * @param scanner Scanner, the scanner to read from.
     * @return String, the next string from the scanner.
     */
    public String nextString(Scanner scanner) {
        try {
            return scanner.next();
        } catch (InputMismatchException e) {
            handleInputMismatch("string", scanner);
            return nextString(scanner);
        } finally {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Consume any remaining input
            }
        }
    }

    /**
     * Reads the next integer from the provided scanner.
     *
     * @param scanner Scanner, the scanner to read from.
     * @return int, the next integer from the scanner.
     */
    public int nextInt(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            handleInputMismatch("integer", scanner);
            return nextInt(scanner);
        } finally {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Consume any remaining input
            }
        }
    }

    public void printOptionTable(HashMap<Integer, String> options) {
        System.out.println("+--------------------------------+");
        System.out.println("|            OPTIONS             |");
        System.out.println("+--------------------------------+");

        for (Integer key : options.keySet()) {
            String option = String.format("| %-2d | %-26s |", key, options.get(key));
            System.out.println(option);
        }

        System.out.println("+--------------------------------+");
    }

    /**
     * Gets the user's choice by displaying a prompt and reading from the provided scanner.
     *
     * @param prompt  String, the prompt to display to the user.
     * @param scanner Scanner, the scanner to read from.
     * @return int, the user's input choice.
     */
    public int getUserChoice(String prompt, Scanner scanner) {
        int input = -1;

        try {
            System.out.print(prompt);
            input = scanner.nextInt();

            if (!isValidOption(input)) {
                handleInvalidInput(scanner);
                input = getUserChoice(prompt, scanner);
            }

        } catch (InputMismatchException e) {
            handleInputMismatch("integer", scanner);
            input = getUserChoice(prompt, scanner);
        } finally {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Consume any remaining input if there is any
            }
        }
        return input;
    }

    /**
     * Handles an input mismatch by displaying an error message and consuming the invalid input.
     *
     * @param expectedType String, the expected type of input.
     * @param scanner      Scanner, the scanner where the mismatch occurred.
     */
    private void handleInputMismatch(String expectedType, Scanner scanner) {
        System.out.println("Invalid input. Please enter a valid " + expectedType + ".");
        scanner.nextLine(); // Consume the invalid input
    }

    /**
     * Handles an invalid input by displaying an error message.
     *
     * @param scanner Scanner, the scanner where the invalid input occurred.
     */
    private void handleInvalidInput(Scanner scanner) {
        System.out.println("Invalid input. Please enter a valid option.");
    }

    /**
     * Runs the user menu, displaying options, getting user input, and executing the chosen option.
     */
    public void run() {
        exitFlag = false;

        try (Scanner localScanner = new Scanner(System.in)) {
            while (!exitFlag) {
                // Display menu and get user choice
                displayMenu();

                // Get user choice
                int option = getUserChoice("Enter option: ", localScanner);

                // Check if the user wants to exit the menu.
                if (option == options.size()) {
                    exit();
                    continue;
                }
                //  Execute the user's choice if it is not the exit option
                chooseOption(option);
            }
        } // Scanner is automatically closed when the try block is exited
    }
}
