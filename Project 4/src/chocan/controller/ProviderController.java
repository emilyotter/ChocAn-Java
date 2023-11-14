/**
 * This class represents the Provider Controller which is responsible for handling the interactions between the Provider and the ChocAn system.
 * It extends the AbstractController class and implements the following functionalities:
 * - Validate member
 * - Bill ChocAn
 * - Add service
 * - Update service
 * - Delete service
 * - Generate provider report
 * - Email provider report
 * It uses an InputHandler to get input from the user and a ServiceDatabase to store and retrieve service data.
 * @param userDatabase The CredentialsDatabase object used to validate user credentials.
 * @param serviceDatabase The ServiceDatabase object used to store and retrieve service data.
 */
package chocan.controller;

import chocan.database.CredentialsDatabase;
import chocan.database.ServiceDatabase;
import chocan.handler.InputHandler;

import java.util.HashMap;

public class ProviderController extends AbstractController{

    // Input Handler for the Provider Controller
    private InputHandler inputHandler = new InputHandler();

    // Service Database for the Provider Controller
    ServiceDatabase serviceDatabase;

    /**
     * Constructor for ProviderController class.
     * @param userDatabase The CredentialsDatabase object used to validate user credentials.
     * @param serviceDatabase The ServiceDatabase object used to store and retrieve service data.
     */
    public ProviderController(CredentialsDatabase userDatabase, ServiceDatabase serviceDatabase) {
        super(userDatabase);
        this.serviceDatabase = serviceDatabase;
    }

    /**
     * Validates a member's card by checking if the member number exists in the user database.
     */
    public void validateMember() {
        String input;
        System.out.println(); //member number
        input = inputHandler.unconstrainedPromptStr("Provide card (Member number): " );
        
        // Get the member data from the database
        try {
            HashMap<String, String> data = userDatabase.getEntry(input);
            if (data.get("role").equals("member")) {
                System.out.println("Member found: " + data.get("name"));
            } else {
                System.out.println("Not a member!");
            }
        } catch(IllegalArgumentException  e) {
            System.out.println("Invalid member number.");
        }

    }

    /**
     * Bills ChocAn for a service provided by the provider.
     */
    public void billChocAn() {
        // TODO: Implement billing functionality
    }

    /**
     * Adds a new service to the service database.
     */
    public void addService() {
        HashMap<String, String> data = new HashMap<>();

        // Use the input handler to get the data from the user
        data.put("serviceCode", inputHandler.unconstrainedPromptStr("Enter the service code"));
        data.put("dateOfService", inputHandler.unconstrainedPromptStr("Enter the date of service"));
        data.put("memberId", inputHandler.unconstrainedPromptStr("Enter the member's id"));
        data.put("providerId", inputHandler.unconstrainedPromptStr("Enter the provider's id"));
        data.put("fee", inputHandler.unconstrainedPromptStr("Enter the fee amount"));

        // Get Unique Service ID
        String key = serviceDatabase.generateUniqueID();

        // Transaction Successful
        System.out.println("Service added successfully. The Transaction ID is: " + key);

        // Add using service id
        serviceDatabase.addEntry(key, data);
    }

    /**
     * Updates an existing service in the service database.
     */
    public void updateService() {
        HashMap<String, String> data = new HashMap<>();
        String input = inputHandler.unconstrainedPromptStr("Enter the ID of the service you wish to edit: ");
        try {
            serviceDatabase.removeEntry(input);

            // Use the input handler to get the data from the user
            data.put("serviceCode", inputHandler.unconstrainedPromptStr("Enter the service code"));
            data.put("dateOfService", inputHandler.unconstrainedPromptStr("Enter the date of service"));
            data.put("memberId", inputHandler.unconstrainedPromptStr("Enter the member's id"));
            data.put("providerId", inputHandler.unconstrainedPromptStr("Enter the provider's id"));
            data.put("fee", inputHandler.unconstrainedPromptStr("Enter the fee amount"));

            // Update the entry
            serviceDatabase.updateEntry(input, data);

            // Transaction Successful
            System.out.println("Transaction ID :" + input + " updated successfully.");
            
        } catch (IllegalArgumentException  e) {
            System.out.println("Service not found are you sure the ID is correct?");
        }
    }

    /**
     * Deletes an existing service from the service database.
     */
    public void deleteService() {
        String input = inputHandler.unconstrainedPromptStr("Enter the ID of the service you wish to remove: ");
        try {
            serviceDatabase.removeEntry(input);
        } catch (IllegalArgumentException e) {
            System.out.println("Service not found are you sure the ID is correct?");
        }
    }

    /**
     * Generates a report for the provider.
     */
    public void generateProviderReport() {
        // TODO: Implement report generation functionality
    }

    /**
     * Emails a report to the provider.
     */
    public void emailProviderReport() {
        // Send email to the user
        String input = inputHandler.unconstrainedPromptStr("Enter the ID of the user you wish to email: ");
        try {
            HashMap<String, String> data = userDatabase.getEntry(input);
            System.out.println("Email was sent to " + data.get("name"));
        } catch(IllegalArgumentException  e) {
            System.out.println("User not found are you sure the ID is correct.");
        }
    }
}
