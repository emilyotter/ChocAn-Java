// Maddox Guthrie 11/3/2023
// Entry point for the Chocoholics Anonymous system terminal.
// This is the main class for the terminal, it will be used to run the terminal.

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import chocan.database.CredentialsDatabase;
import chocan.timer.DailyTimer;

public class Terminal {
	
	private static final Logger logger = LoggerFactory.getLogger(Terminal.class);
	
    public static void main(String[] args) {
    	logger.debug("[MAIN] Start"); // test

        if(!turnOn()) {
            System.out.println("An error occurred, shutting off the system.");
            turnOff(-1);
        }

        //DailyTimer midnightTimer = new DailyTimer(24, 0, 0, REPORT_CONTROLLER_FROM_ABSTRACT_CONTROLLER);
        //midnightTimer.start();

        CredentialsDatabase db = new CredentialsDatabase();
        db.printAll();

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