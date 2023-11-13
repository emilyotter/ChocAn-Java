package chocan.controller;

import chocan.database.CredentialsDatabase;
import chocan.menu.ManagerMenu;
import chocan.menu.OperatorMenu;
import chocan.menu.ProviderMenu;
import chocan.menu.UserMenu;

import java.util.Scanner;

public class LoginController extends AbstractController {

    private UserMenu userMenu;

    public LoginController(CredentialsDatabase userDatabase) {super(userDatabase);}

    /**
     * Authenticates a user based on the provided credentials.
     *
     * @param id       The user ID.
     * @param password The user password.
     * @return True if authentication is successful, false otherwise.
     */
    private boolean authenticateUser(String id, String password) {
        return userDatabase.authenticateCredentials(id, password);
    }

    private void initializeUserMenu(String role) {
        switch (role) {
            case "provider":
                ProviderController providerController = new ProviderController(userDatabase);
                userMenu = new ProviderMenu(providerController);
                break;
            case "operator":
                OperatorController operatorController = new OperatorController(userDatabase);
                userMenu = new OperatorMenu(operatorController);
                break;
            case "manager":
                ManagerController managerController = new ManagerController(userDatabase);
                userMenu = new ManagerMenu(managerController);
                break;
            default:
                break;
        }
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your ChocAn provided ID and Password.");

        boolean loggedIn = false;
        do {
            System.out.println("Enter ID: ");
            String id = scanner.nextLine();
            System.out.println("Enter Password: ");
            String password = scanner.nextLine();

            loggedIn = authenticateUser(id, password);

            if (loggedIn) {
                // If the user is authenticated, get the role and proceed accordingly
                String role = userDatabase.getRole(id);
                System.out.println("Login successful. You are logged in as a " + role + ".");
                initializeUserMenu(role);
                // Run the user menu for the user's role
                userMenu.run();
            } else {
                // Authentication failed
                System.out.println("Invalid ID or Password. Please try again.");
            }

        } while (!loggedIn);

        //scanner.close();
    }

}
