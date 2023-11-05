// Maddox Guthrie 11/3/2023
// Entry point for the Chocoholics Anonymous system terminal.
// This is the main class for the terminal, it will be used to run the terminal.
import chocan.database.CredentialsDatabase;

public class Terminal {
    public static void main(String[] args) {
        System.out.println("Welcome to the Chocoholics Anonymous system terminal.");
        CredentialsDatabase db = new CredentialsDatabase();
        db.printAll();

        if(!turnOn()) {
            System.out.println("An error occurred, shutting off the system.");
            turnOff(-1);
        }

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