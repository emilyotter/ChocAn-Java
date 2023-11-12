package chocan.menu;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.HashMap;
import chocan.controller.AbstractController;

/**
 * Abstract class representing a user menu in the ChocAn system.
 */
public abstract class UserMenu extends AbstractMenu {

    /*
     * Exit value for the menu.
     */
    protected int EXIT_VALUE ;

    /**
     * Flag to exit the menu.
     */
    public boolean exitFlag;

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
        if (options.isEmpty()) {
            this.EXIT_VALUE = 1;
        } else {
            this.EXIT_VALUE = options.keySet().stream().max(Integer::compare).get() + 1;
        }

        // Add exit option to the greatest key + 1 (to avoid collisions)
        options.put(this.EXIT_VALUE, "Exit");
        
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
     * Reads the next integer from the provided scanner, handling invalid input types.
     *
     * @param scanner Scanner, the scanner to read from.
     * @return int, the next integer read from the scanner.
     */
    public int nextInt(Scanner scanner) {
        int input = -1;
        // Loop valid flag
        boolean validInput = false;
        // Loop until the user enters a valid input
        while (!validInput) {
            try {
                input = scanner.nextInt(); // Read the user's input
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please try again.");
                consumeRemainingInput(scanner); // Consume any remaining input if there is any
            }
        }

        return input;

    }


    /**
     * Reads the next string from the provided scanner, handling invalid input types.
     *
     * @param scanner Scanner, the scanner to read from.
     * @return String, the next string read from the scanner.
     */
    public String nextString(Scanner scanner) {
        String input = "";
        // Loop valid flag
        boolean validInput = false;
        // Loop until the user enters a valid input
        while (!validInput) {
            try {
                input = scanner.nextLine(); // Read the user's input
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please try again.");
                consumeRemainingInput(scanner); // Consume any remaining input if there is any
            }
        }

        return input;
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

        // Loop valid flag
        boolean validInput = false;

        // Loop until the user enters a valid input
        while (!validInput) {
            System.out.print(prompt);

            // Get the user's input
            input = nextInt(scanner);

            // Check if the input is valid
            if (isValidOption(input)) {
                validInput = true;
            } else {
                System.out.println("Invalid option. Please try one of the below option.");
                displayMenu();
                consumeRemainingInput(scanner);
            }
        }

        return input;
    }
    
    /*
     * Consumen any remaining input if there is any.
     */
    public void consumeRemainingInput(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine(); // Consume any remaining input if there is any
        }
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
                if (option == EXIT_VALUE) {
                    exit();
                    continue;
                }
                //  Execute the user's choice if it is not the exit option
                chooseOption(option);
            }
        } // Scanner is automatically closed when the try block is exited
    }
}
