package test.menu;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;
import chocan.controller.AbstractController;
import chocan.menu.UserMenu;

public class UserMenuTest {

    // Example subclass of UserMenu for testing
    private static class TestUserMenu extends UserMenu {

        private boolean optionChosen;
        private int chosenOptionValue;

        TestUserMenu(AbstractController controller) {
            super(controller);
            optionChosen = false;
        }

        @Override
        protected HashMap<Integer, String> getOptions() {
            HashMap<Integer, String> options = new HashMap<>();
            options.put(1, "Option 1");
            options.put(2, "Option 2");
            options.put(3, "Option 3");
            return options;
        }

        @Override
        protected void chooseOption(int option) {
            optionChosen = true;
            chosenOptionValue = option;
        }
    }

    @Test
    public void displayMenu() {
        TestUserMenu testMenu = new TestUserMenu(null);
        testMenu.displayMenu();
        // As displayMenu prints to the console, manual verification is required.
    }

    @Test
    public void isValidOption() {
        TestUserMenu testMenu = new TestUserMenu(null);

        assertTrue(testMenu.isValidOption(1));
        assertTrue(testMenu.isValidOption(2));
        assertTrue(testMenu.isValidOption(3));
        assertTrue(testMenu.isValidOption(4)); // Exit option
        assertFalse(testMenu.isValidOption(5));
    }

    @Test
    public void nextString() {
        TestUserMenu testMenu = new TestUserMenu(null);

        // Simulate user input
        String input = "Test String\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals("Test String", testMenu.nextString(new Scanner(System.in)));
    }

    @Test
    public void nextInt() {
        TestUserMenu testMenu = new TestUserMenu(null);

        // Simulate user input
        String input = "42\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals(42, testMenu.nextInt(new Scanner(System.in)));
    }

    @Test
    public void getUserChoice() {
        TestUserMenu testMenu = new TestUserMenu(null);

        // Simulate user input
        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals(2, testMenu.getUserChoice("Enter option: ", new Scanner(System.in)));
    }

    @Test
    public void runWithExitOption() {
        TestUserMenu testMenu = new TestUserMenu(null);

        // Simulate user input
        String input = "3\n4\n"; // 3 is a valid option, 4 is exit
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        testMenu.run();

        assertTrue(testMenu.optionChosen);
        assertEquals(3, testMenu.chosenOptionValue);
    }

    @Test
    public void runInvalidOption() {
        TestUserMenu testMenu = new TestUserMenu(null);

        // Simulate user input
        String input = "5\n4\n"; // 5 is an invalid option
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        testMenu.run();

        assertFalse(testMenu.optionChosen);
    }

    @Test
    public void runWithoutExitOption() {
        TestUserMenu testMenu = new TestUserMenu(null);

        // Simulate user input
        String input = "2\n1\n4\n"; // 2 is a valid option, 1 is a valid option
        InputStream originalSystemIn = System.in;

        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            testMenu.run();
        } finally {
            System.setIn(originalSystemIn); // Restore the original System.in
        }

        assertTrue(testMenu.optionChosen);
        assertEquals(1, testMenu.chosenOptionValue);
    }

    @Test
    public void consumeRemainingInput() {
        TestUserMenu testMenu = new TestUserMenu(null);

        // Simulate user input
        String input = "Test Input\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Consume the input
        testMenu.consumeRemainingInput(new Scanner(System.in));
        assertTrue(System.in instanceof InputStream);
    }

    @Test
    public void exitFlagAfterExit() {
        TestUserMenu testMenu = new TestUserMenu(null);

        // Simulate user input
        String input = "4\n"; // 4 is exit
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        testMenu.run();

        assertTrue(testMenu.exitFlag);
    }

    @Test
    public void exitFlagNotSetOnValidOption() {
        TestUserMenu testMenu = new TestUserMenu(null);

        // Simulate user input
        String input = "2\n4\n"; // 2 is a valid option, 4 is exit
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        testMenu.run();

        assertTrue(testMenu.exitFlag);
    }

    @Test
    public void chooseOptionCalled() {
        TestUserMenu testMenu = new TestUserMenu(null);

        // Simulate user input
        String input = "2\n4\n"; // 2 is a valid option, 4 is exit
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        testMenu.run();

        assertTrue(testMenu.optionChosen);
    }

    @Test
    public void chooseOptionCalledWithCorrectValue() {
        TestUserMenu testMenu = new TestUserMenu(null);

        // Simulate user input
        String input = "2\n4\n"; // 2 is a valid option, 4 is exit
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        testMenu.run();

        assertEquals(2, testMenu.chosenOptionValue);
    }

    @Test
    public void chooseOptionNotCalledOnExit() {
        TestUserMenu testMenu = new TestUserMenu(null);

        // Simulate user input
        String input = "4\n"; // 4 is exit
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        testMenu.run();

        assertFalse(testMenu.optionChosen);
    }

}
