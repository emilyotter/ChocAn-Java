package chocan.menu;
import chocan.controller.ManagerController;
import chocan.controller.OperatorController;
import chocan.controller.ProviderController;
import chocan.database.CredentialsDatabase;
import java.util.Scanner;

/**
 * Class representing the login menu for the ChocAn system.
 */
public class LoginMenu {
    private final CredentialsDatabase credentialsDatabase;
    private UserMenu userMenu;

    /**
     * Constructor for the LoginMenu class.
     *
     * @param credentialsDatabase The database containing user credentials.
     */
    public LoginMenu(CredentialsDatabase credentialsDatabase) {
        this.credentialsDatabase = credentialsDatabase;
    }

    /**
     * Displays the login menu and handles user authentication.
     */
    public void showLoginMenu() {
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
                String role = credentialsDatabase.getRole(id);
                System.out.println("Login successful. You are logged in as a " + role + ".");
                initializeUserMenu(role);
                // Run the user menu for the user's role
                userMenu.run();
            } else {
                // Authentication failed
                System.out.println("Invalid ID or Password. Please try again.");
            }

        } while (!loggedIn);

        scanner.close();
    }

    /**
     * Authenticates a user based on the provided credentials.
     *
     * @param id       The user ID.
     * @param password The user password.
     * @return True if authentication is successful, false otherwise.
     */
    private boolean authenticateUser(String id, String password) {
        return credentialsDatabase.authenticateCredentials(id, password);
    }

    /**
     * Initializes the user menu based on the user's role.
     *
     * @param role The user's role.
     */
    private void initializeUserMenu(String role) {
        switch (role) {
            case "provider":
                ProviderController providerController = new ProviderController(credentialsDatabase);
                userMenu = new ProviderMenu(providerController);
                break;
            case "operator":
                OperatorController operatorController = new OperatorController(credentialsDatabase);
                userMenu = new OperatorMenu(operatorController);
                break;
            case "manager":
                ManagerController managerController = new ManagerController(credentialsDatabase);
                userMenu = new ManagerMenu(managerController);
                break;
            default:
                break;
        }
    }
}
