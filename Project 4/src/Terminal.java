// Maddox Guthrie 11/3/2023
// Entry point for the Chocoholics Anonymous system terminal.
// This is the main class for the terminal, it will be used to run the terminal.

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Terminal {
	
	private static final Logger logger = LoggerFactory.getLogger(Terminal.class);
	
    public static void main(String[] args) {
    	logger.debug("[MAIN] Start");
        System.out.println("Welcome to the Chocoholics Anonymous system terminal.");
        turnOff();
    }

    private static void turnOff() {
        System.out.println("Exiting the system, Thank you for choosing Chocoholics Anonymous.");
        System.exit(0);
    }

}