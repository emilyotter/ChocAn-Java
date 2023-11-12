package chocan.controller;


import chocan.database.CredentialsDatabase;

import java.util.HashMap;
import java.util.Scanner;

public class ProviderController extends AbstractController{

    public ProviderController(CredentialsDatabase userDatabase) {
        super(userDatabase);
    }
    
    public void validateMember() {

    }
    
    public void billChocAn() {
    	
    }
    
    public void requestProviderDirectory() {
    	
    }
    
    public void searchCode() {
    	
    }
    
    public void searchServiceFee() {
    	
    }
    
    public void emailProviderReport() {
    	//getting report to send as attachment goes here once that is finished
    	System.out.println("Enter the Report Recipient's ID");
        String input = inputScanner.nextLine();
        try {
        	HashMap<String, String> data = userDatabase.getEntry(input);
        	System.out.println("Email was sent to " + data.get("name"));
        } catch(IllegalArgumentException  e) {
        	System.out.println("User not found are you sure the ID is correct.");
        }
    }
}
