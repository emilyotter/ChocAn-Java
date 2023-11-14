package chocan.controller;

import chocan.database.CredentialsDatabase;
import chocan.database.ServiceDatabase;
import chocan.menu.ManagerMenu;
import chocan.menu.OperatorMenu;
import chocan.menu.ProviderMenu;
import chocan.menu.UserMenu;

import chocan.handler.InputHandler;

public class LoginController extends AbstractController {

    // User menu for the user's role
    private UserMenu userMenu;

    // Serivce database
    private ServiceDatabase serviceDatabase;

    // Input Handler
    private InputHandler inputHandler;



    public LoginController(CredentialsDatabase userDatabase, ServiceDatabase serviceDatabase) {
        super(userDatabase);
        this.serviceDatabase = serviceDatabase;
        inputHandler = new InputHandler();
        
    }

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
                ProviderController providerController = new ProviderController(userDatabase, serviceDatabase);
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
        boolean loggedIn = false;
        do {
            String id = this.inputHandler.unconstrainedPromptStr("Enter ID: ");
            String password = this.inputHandler.unconstrainedPromptStr("Enter Password: ");

            loggedIn = authenticateUser(id, password);

            if (loggedIn) {
                // If the user is authenticated, get the role and proceed accordingly
                String role = userDatabase.getRole(id);

                // If the role is null or member, the user cannot log in
                if (role == null || role.equals("member")) {
                    System.out.println("Invalid Role: User cannot log in. Please contact the system administrator!");
                    return;
                }

                // Authentication successful
                System.out.println("Login successful. You are logged in as a " + role + ".");
                initializeUserMenu(role);
    
                // Run the user menu for the user's role
                userMenu.run();
                
            } else {
                // Authentication failed
                System.out.println("Invalid ID or Password. Please try again.");
            }

        } while (!loggedIn);

    }

}
