package chocan.controller;


import chocan.database.CredentialsDatabase;

import java.util.HashMap;

import chocan.handler.InputHandler;

public class OperatorController extends AbstractController {

    // Input Handler for the Operator Controller
    private InputHandler inputHandler = new InputHandler();


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

    }
}
