package chocan.menu;

import chocan.controller.ManagerController;
import chocan.controller.OperatorController;
import chocan.controller.ProviderController;
import chocan.database.CredentialsDatabase;


//import java.util.HashMap;
import java.util.Scanner;

public class LoginMenu {
    private final CredentialsDatabase credentialsDatabase;

    // User controller for the menu which is set based on the user's role
    private UserMenu userMenu;

    // Constructor
    public LoginMenu(CredentialsDatabase credentialsDatabase) {
        this.credentialsDatabase = credentialsDatabase;
    }

    // Method to show the login menu and handle user authentication
    public void showLoginMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your ChocAn provided ID and Password.");
        
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("Enter ID: ");
            String id = scanner.nextLine();
            System.out.println("Enter Password: ");
            String password = scanner.nextLine();

            loggedIn = credentialsDatabase.authenticateCredentials(id, password);

            if (loggedIn) {
                // If the user is authenticated, get the role and proceed accordingly
                String role = credentialsDatabase.getRole(id);
                System.out.println("Login successful. You are logged in as a " + role + ".");
                switch (role) {
                    case "provider":
                        ProviderController providerController = new ProviderController(credentialsDatabase);
                        userMenu = new ProviderMenu(providerController);
                        userMenu.displayMenu();
                        break;

                    case "operator":
                        OperatorController operatorController = new OperatorController(credentialsDatabase);
                        userMenu = new OperatorMenu(operatorController);
                        userMenu.displayMenu();
                        break;

                    case "manager":
                        ManagerController managerController = new ManagerController(credentialsDatabase);
                        userMenu = new ManagerMenu(managerController);
                        userMenu.displayMenu();
                        break;

                    default:
                        break;

                }

            } else {
                // Authentication failed
                System.out.println("Invalid ID or Password. Please try again.");
                showLoginMenu();
            }

        // Run the user menu here 
        userMenu.run();
        
        }

        scanner.close();;
    }
}