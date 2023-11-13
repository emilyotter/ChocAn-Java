package chocan.controller;


import chocan.database.CredentialsDatabase;
import chocan.database.ServiceDatabase;

import java.util.HashMap;
import java.util.Scanner;

public class ProviderController extends AbstractController{
	
	ServiceDatabase serviceDatabase;

    public ProviderController(CredentialsDatabase userDatabase, ServiceDatabase serviceDatabase) {
        super(userDatabase);
        this.serviceDatabase = serviceDatabase;
    }
    
    public void validateMember() {
    	Scanner inputScanner = new Scanner(System.in);
        String input;
    	System.out.println("Provide card (Member number)"); //member number
    	input = inputScanner.nextLine();
    	try {
    		if(userDatabase.entryExists(input)) System.out.println("Validated");
    		
    	} catch (IllegalArgumentException  e) {
            System.out.println("Invalid Number");
        }
    }
    
    public void billChocAn() {
    	
    }
    
    public void addService() {
    	HashMap<String, String> data = new HashMap<>();
        Scanner inputScanner = new Scanner(System.in);
        String input;
        String key;

        System.out.println("Enter the service code");
        input = inputScanner.nextLine();
        data.put("serviceCode", input);

        System.out.println("Enter the date of service");
        input = inputScanner.nextLine();
        data.put("dateOfService", input);

        System.out.println("Enter the member's id");
        input = inputScanner.nextLine();
        data.put("memberId", input);

        System.out.println("Enter the provider's id");
        input = inputScanner.nextLine();
        data.put("providerId", input);

        System.out.println("Enter the fee amount");
        input = inputScanner.nextLine();
        data.put("fee", input);

        //generate some service id
        System.out.println("Service added successfully");

        //add using service id
        //userDatabase.addEntry(key, data);

    }
    
    public void updateService() {
    	Scanner inputScanner = new Scanner(System.in);
        HashMap<String, String> data = new HashMap<>();
        System.out.println("Enter the ID of the service you wish to edit");
        String input = inputScanner.nextLine();
        try {
            serviceDatabase.removeEntry(input);

            System.out.println("Enter the service code");
            input = inputScanner.nextLine();
            data.put("serviceCode", input);

            System.out.println("Enter the date of service");
            input = inputScanner.nextLine();
            data.put("dateOfService", input);

            System.out.println("Enter the member's id");
            input = inputScanner.nextLine();
            data.put("memberId", input);

            System.out.println("Enter the provider's id");
            input = inputScanner.nextLine();
            data.put("providerId", input);

            System.out.println("Enter the fee amount");
            input = inputScanner.nextLine();
            data.put("fee", input);

            //generate some service id
            System.out.println("Service added successfully");
            //add using service id
            //serviceDatabaseDatabase.addEntry(key, data);

        } catch (IllegalArgumentException  e) {
            System.out.println("Service not found are you sure the ID is correct?");
        }
    }
    
    public void deleteService() {
    	Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter the ID of the service you wish to remove");
        String input = inputScanner.nextLine();
        try {
            serviceDatabase.removeEntry(input);
        } catch (IllegalArgumentException e) {
            System.out.println("Service not found are you sure the ID is correct?");
        }
    }
    
    public void generateProviderReport() {
    	
    }
    
    public void emailProviderReport() {
    	//getting report to send as attachment goes here once that is finished
    	System.out.println("Enter the Report Recipient's ID");
        // Send email to the user
        Scanner inputScanner = new Scanner(System.in);
        String input = inputScanner.nextLine();
        try {
        	HashMap<String, String> data = userDatabase.getEntry(input);
        	System.out.println("Email was sent to " + data.get("name"));
        } catch(IllegalArgumentException  e) {
        	System.out.println("User not found are you sure the ID is correct.");
        }
    }
}
