// Maddox Guthrie 11/3/2023
// Entry point for the Chocoholics Anonymous system terminal.
// This is the main class for the terminal, it will be used to run the terminal.

import java.util.HashMap;
import chocan.controller.LoginController;
import chocan.database.CredentialsDatabase;
import chocan.database.ServiceDatabase;
import chocan.menu.LoginMenu;
import chocan.DailyTimer;
import chocan.controller.MemberReportController;
import chocan.controller.SummaryReportController;
import chocan.controller.ProviderReportController;


public class Terminal {
    public static void main(String[] args) {
        if(!turnOn()) {
            System.out.println("An error occurred, shutting off the system.");
            turnOff(-1);
        }

        // Create the databases
        CredentialsDatabase termCredentialsDatabase = new CredentialsDatabase();
        ServiceDatabase termServiceDatabase = new ServiceDatabase();

        // Bootstrap the admin account
        bootstrapAdmin(termCredentialsDatabase);

        // Create the controllers
        MemberReportController mrc = new MemberReportController(termCredentialsDatabase , termServiceDatabase);
        ProviderReportController prc = new ProviderReportController(termCredentialsDatabase, termServiceDatabase);
        SummaryReportController src = new SummaryReportController(termCredentialsDatabase, termServiceDatabase);

        DailyTimer mrcTimer = new DailyTimer(7, 24, 0, 0, mrc);
        DailyTimer prcTimer = new DailyTimer(7, 24, 0, 0, prc);
        DailyTimer srcTimer = new DailyTimer(7, 24, 0, 0, src);

        // Start the timers
        mrcTimer.start();
        prcTimer.start();
        srcTimer.start();
        
        
        // Create the login menu
        LoginController userLoginController = new LoginController(termCredentialsDatabase, termServiceDatabase);
        LoginMenu userLoginMenu = new LoginMenu(userLoginController);
        userLoginMenu.run();


        // Turn off the system
        turnOff();
    }

    /**
     * This method is used to bootstrap the system with an admin account.
     * The admin account has the following credentials:
     * UID = admin
     * password = admin
     * role = operator
     * address = 123 Penny Lane
     * zipcode = 12345
     * state = AL
     * 
     * @param termCredentialsDatabase The credentials database to add the admin account to.
     */
    private static void bootstrapAdmin(CredentialsDatabase termCredentialsDatabase) {
        // Add Admin Credentials for bootstrapping
        HashMap<String, String> adminCredentials = new HashMap<String, String>();
        adminCredentials.put("name", "admin");
        adminCredentials.put("password", "admin");
        adminCredentials.put("role", "operator");
        adminCredentials.put("address", "123 Penny Lane");
        adminCredentials.put("zipcode", "12345");
        adminCredentials.put("state", "AL");
        
        try {
            termCredentialsDatabase.addEntry("admin", adminCredentials);
        } catch (Exception IllegalArgumentException) {
            return;
        }

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