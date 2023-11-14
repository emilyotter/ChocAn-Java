/*
 * Authors:
 * Name:        Nichal Bhattarai
 * CWID:        12088410
 * Email:       nbhattarai@crimson.ua.edu
 *
 * Contributors:
 *              Maddox Guthrie
 */

package chocan.menu;

import java.util.HashMap;
import chocan.controller.AbstractController;
import chocan.handler.InputHandler;

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

    /*
     * InputHandler for the menu.
     */
    protected InputHandler inputHandler;

    /**
     * Constructs a UserMenu with the specified controller.
     *
     * @param controller AbstractController, the controller for this menu.
     */
    public UserMenu(AbstractController controller) {
        super(controller);

        // Initialize options HashMap
        this.options = getOptions();

        // Check if the keys are strictly in accending order from 1 to n
        for (int i = 1; i <= options.size(); i++) {
            if (!options.containsKey(i)) {
                throw new IllegalArgumentException("Menu options must be strictly in ascending order from 1 to n.");
            }
        }

        // Union Exit option to the greatest key + 1 (to avoid collisions)
        if (options.isEmpty()) {
            this.EXIT_VALUE = 1;
        } else {
            this.EXIT_VALUE = options.keySet().stream().max(Integer::compare).get() + 1;
        }

        // Add exit option to the greatest key + 1 (to avoid collisions)
        options.put(this.EXIT_VALUE, "Exit");

        // Initialize InputHandler
        this.inputHandler = new InputHandler();
        
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
     * Runs the user menu, displaying options, getting user input, and executing the chosen option.
     */
    public void run() {
        exitFlag = false;

            while (!exitFlag) {
                // Display menu and get user choice
                displayMenu();

                // Get user choice
                int option = this.inputHandler.constrainedPromptInt("Enter an option: ", 1, EXIT_VALUE);

                // Check if the user wants to exit the menu.
                if (option == EXIT_VALUE) {
                    exit();
                    return;
                }
                //  Execute the user's choice if it is not the exit option
                chooseOption(option);
            }
        }
}
