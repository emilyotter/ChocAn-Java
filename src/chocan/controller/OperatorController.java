// Maddox Guthrie 11/14/2023

package chocan.controller;

import chocan.database.CredentialsDatabase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import chocan.handler.InputHandler;

public class OperatorController extends AbstractController {

    // Input Handler for the Operator Controller
    private InputHandler inputHandler = new InputHandler();

    private static final String CHOCAN_DIR_ENV_VAR = "CHOCAN_DIR_ENV_VAR";

    public OperatorController(CredentialsDatabase userDatabase) {
        super(userDatabase);
    }

    public void addMember() {
        HashMap<String, String> data = new HashMap<>();
        data.put("role", "member");
        

        String input = this.inputHandler.unconstrainedPromptStr("Enter the member's full name: ");
        data.put("name", input);

        input = inputHandler.unconstrainedPromptStr("Enter the member's password: ");
        data.put("password", input);

        input = inputHandler.unconstrainedPromptStr("Enter the member's street address: ");
        data.put("address", input);

        input = inputHandler.unconstrainedPromptStr("Enter the member's zipcode: ");
        data.put("zipcode", input);

        
        input = inputHandler.unconstrainedPromptStr("Enter the member's state abbreviation: ");
        data.put("state", input);

        String key = userDatabase.generateUniqueID();
        System.out.println("The User's Unique ID is: " + key);

        
        userDatabase.addEntry(key, data);

    }

    public void updateMember() {
        HashMap<String, String> data = new HashMap<>();
        String input = this.inputHandler.unconstrainedPromptStr("Enter the ID of the member you wish to edit: ");
        try {
            userDatabase.removeEntry(input);

            String key = input;
            data.put("role", "member");

            input = inputHandler.unconstrainedPromptStr("Enter the member's full name: ");
            data.put("name", input);

            input = inputHandler.unconstrainedPromptStr("Enter the member's password: ");
            data.put("password", input);

            input = inputHandler.unconstrainedPromptStr("Enter the member's street address: ");
            data.put("address", input);

            input = inputHandler.unconstrainedPromptStr("Enter the member's zipcode: ");
            data.put("zipcode", input);

            input = inputHandler.unconstrainedPromptStr("Enter the member's state abbreviation: ");
            data.put("state", input);

            userDatabase.addEntry(key, data);

        } catch (IllegalArgumentException  e) {
            System.out.println("User not found are you sure the ID is correct.");
        }

    }

    public void deleteMember() {
        String input = inputHandler.unconstrainedPromptStr("Enter the ID of the member you wish to remove: ");
        try {
            userDatabase.removeEntry(input);
        } catch (IllegalArgumentException e) {
            System.out.println("User not found are you sure the ID is correct.");
        }
    }

    public void addProvider() {
        HashMap<String, String> data = new HashMap<>();
        data.put("role", "provider");
        String input;
        String key;

        input = inputHandler.unconstrainedPromptStr("Enter the provider's full name: ");
        data.put("name", input);

        input = inputHandler.unconstrainedPromptStr("Enter the provider's password: ");
        data.put("password", input);

        input = inputHandler.unconstrainedPromptStr("Enter the provider's street address: ");
        data.put("address", input);

        input = inputHandler.unconstrainedPromptStr("Enter the provider's zipcode: ");
        data.put("zipcode", input);

        input = inputHandler.unconstrainedPromptStr("Enter the provider's state abbreviation: ");
        data.put("state", input);

        input = inputHandler.unconstrainedPromptStr("Enter the provider's ID number: ");
        key = userDatabase.generateUniqueID();
        System.out.println("The User's Unique ID is: " + key);

        userDatabase.addEntry(key, data);


    }

    public void updateProvider() {
        HashMap<String, String> data = new HashMap<>();
        String input = inputHandler.unconstrainedPromptStr("Enter the ID of the provider you wish to edit: ");
        try {
            userDatabase.removeEntry(input);

            String key = input;
            data.put("role", "provider");

            input = inputHandler.unconstrainedPromptStr("Enter the provider's full name: ");
            data.put("name", input);

            input = inputHandler.unconstrainedPromptStr("Enter the provider's password: ");
            data.put("password", input);

            input = inputHandler.unconstrainedPromptStr("Enter the provider's street address: ");
            data.put("address", input);

            input = inputHandler.unconstrainedPromptStr("Enter the provider's zipcode: ");
            data.put("zipcode", input);

            input = inputHandler.unconstrainedPromptStr("Enter the provider's state abbreviation: ");
            data.put("state", input);

            userDatabase.addEntry(key, data);

        } catch (IllegalArgumentException  e) {
            System.out.println("User not found are you sure the ID is correct.");
        }

    }

    public void deleteProvider() {
        String input = inputHandler.unconstrainedPromptStr("Enter the ID of the provider you wish to remove: ");
        try {
            userDatabase.removeEntry(input);
        } catch (IllegalArgumentException e) {
            System.out.println("User not found are you sure the ID is correct.");
        }
    }

    public void generateProviderDirectory() {
        Path pdPath;
        if(System.getProperty(CHOCAN_DIR_ENV_VAR) == null) {
            pdPath = Path.of(System.getProperty("java.io.tmpdir"));
        } else {
            pdPath = Path.of(System.getProperty(CHOCAN_DIR_ENV_VAR));
        }

        pdPath = pdPath.resolve("chocan").resolve("providerDirectory.txt");

        File deleteFile = new File(pdPath.toString());
        deleteFile.delete();

        try (FileWriter fstream = new FileWriter(pdPath.toString(), true); BufferedWriter writer = new BufferedWriter(fstream)) {
            writer.write("Service ID: 121110\n");
            writer.write("Service Name: Basic Therapy\n");
            writer.write("Fee: $50\n");

            writer.write("Service ID: 131211\n");
            writer.write("Service Name: Extensive Therapy\n");
            writer.write("Fee: $100\n");

            writer.write("Service ID: 141312\n");
            writer.write("Service Name: Complete Therapy\n");
            writer.write("Fee: $200\n");

            System.out.println("Service ID: 121110");
            System.out.println("Service Name: Basic Therapy");
            System.out.println("Fee: $50\n");

            System.out.println("Service ID: 131211");
            System.out.println("Service Name: Extensive Therapy");
            System.out.println("Fee: $100\n");

            System.out.println("Service ID: 141312");
            System.out.println("Service Name: Complete Therapy");
            System.out.println("Fee: $200\n");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

    }

    /**
     * Prints the credentials of all users in the user database.
     */
    public void printCredentials() {
        userDatabase.printAllEntries();
    }



    /**
     * Adds a new user account to the user database.
     * Prompts the user for the necessary information, generates a unique ID for the user,
     * and adds the user to the database.
     */
    public void addUserAccount(){
        HashMap<String, String> data = new HashMap<>();
        String input;
        String key;

        input = inputHandler.constrainedPromptStr("Enter user's role: ", this.userDatabase.roleOptions);
        data.put("role", input);

        input = inputHandler.unconstrainedPromptStr("Enter the user's full name: ");
        data.put("name", input);

        input = inputHandler.unconstrainedPromptStr("Enter the user's password: ");
        data.put("password", input);

        input = inputHandler.unconstrainedPromptStr("Enter the user's street address: ");
        data.put("address", input);

        input = inputHandler.unconstrainedPromptStr("Enter the user's zipcode: ");
        data.put("zipcode", input);

        input = inputHandler.unconstrainedPromptStr("Enter the user's state abbreviation: ");
        data.put("state", input);

        key = userDatabase.generateUniqueID();
        System.out.println("The User's Unique ID is: " + key);

        userDatabase.addEntry(key, data);

    }
    
}
