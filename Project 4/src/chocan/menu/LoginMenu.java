package chocan.menu;

import chocan.database.CredentialsDatabase;

//import java.util.HashMap;
import java.util.Scanner;

public class LoginMenu {
    private final CredentialsDatabase credentialsDatabase;

    // Constructor
    public LoginMenu(CredentialsDatabase credentialsDatabase) {
        this.credentialsDatabase = credentialsDatabase;
    }

    // Method to show the login menu and handle user authentication
    public void showLoginMenu() {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Welcome to the ChocAn Login System");
        
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            loggedIn = credentialsDatabase.authenticate(id, password);

            if (loggedIn) {
                // If the user is authenticated, get the role and proceed accordingly
                String role = credentialsDatabase.getRole(id);
                System.out.println("Login successful. You are logged in as a " + role + ".");
                // Here you can redirect the user to different menus based on their role
                // For example:
                // if ("provider".equals(role)) { /* Show provider menu */ }
                // else if ("member".equals(role)) { /* Show member menu */ }
                // ...
            } else {
                // Authentication failed
                System.out.println("Invalid ID or Password. Please try again.");
                showLoginMenu();
            }
        }
        scanner.close();;
    }
}