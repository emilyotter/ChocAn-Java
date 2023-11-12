// Maddox Guthrie 11/3/2023
// Entry point for the Chocoholics Anonymous system terminal.
// This is the main class for the terminal, it will be used to run the terminal.

import java.util.HashMap;

import chocan.database.CredentialsDatabase;
import chocan.menu.LoginMenu;
import chocan.timer.DailyTimer; // For testing

public class Terminal {
    public static void main(String[] args) {
        if(!turnOn()) {
            System.out.println("An error occurred, shutting off the system.");
            turnOff(-1);
        }

        //DailyTimer midnightTimer = new DailyTimer(24, 0, 0, REPORT_CONTROLLER_FROM_ABSTRACT_CONTROLLER);
        //midnightTimer.start();

        CredentialsDatabase termCredentialsDatabase = new CredentialsDatabase();
        LoginMenu termLoginMenu = new LoginMenu(termCredentialsDatabase);
        
        // Print all entries in the database for testing
        termCredentialsDatabase.printAllEntries();

         // Add Admin Credentials for testing
         HashMap<String, String> adminCredentials = new HashMap<String, String>();
         adminCredentials.put("name", "admin");
         adminCredentials.put("password", "admin");
         adminCredentials.put("role", "operator");
         adminCredentials.put("address", "1234");
         adminCredentials.put("zipcode", "100");
         adminCredentials.put("state", "AL");
        
         try {
             termCredentialsDatabase.addEntry("1", adminCredentials);
         } catch (Exception IllegalArgumentException) {
             System.out.println("Entry already exists.");
         }

        
        termLoginMenu.showLoginMenu();

        

        turnOff();
    }

    private static boolean turnOn() {
        System.out.println("Welcome to the Chocoholics Anonymous system terminal.");
        return true;
    }

    // turnOff default call (status = 0)
    private static void turnOff() {
        System.out.println("Exiting the system, Thank you for choosing Chocoholics Anonymous.");
        System.exit(0);
    }

    // turnOff with a provided status number
    private static void turnOff(int status) {
        System.out.println("Exiting the system, Thank you for choosing Chocoholics Anonymous.");
        System.exit(status);
    }

}