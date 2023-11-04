// Maddox Guthrie 11/3/2023
// Entry point for the Chocoholics Anonymous system terminal.
// This is the main class for the terminal, it will be used to run the terminal.


public class Terminal {
    public static void main(String[] args) {
        System.out.println("Welcome to the Chocoholics Anonymous system terminal.");
        turnOff();
    }

    private static void turnOff() {
        System.out.println("Exiting the system, Thank you for choosing Chocoholics Anonymous.");
        System.exit(0);
    }

}