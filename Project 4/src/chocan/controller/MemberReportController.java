package chocan.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import java.nio.file.Path;

import chocan.database.CredentialsDatabase;
import chocan.database.ServiceDatabase;

public class MemberReportController { //extends AbstractReportController
    
	private CredentialsDatabase credentials;
    private ServiceDatabase services;
	
    /*@Override
    public void timedMethod() {

    }*/
   
	public MemberReportController(CredentialsDatabase credentials, ServiceDatabase services) {
    	this.credentials = credentials;
    	this.services = services;
    }
	
	//generates a report for each member as a separate text file
	public void generateMemberReports(List<String> memberIds) throws IOException {
        // Iterate over each member ID and generate a report
        for (String memberId : memberIds) {
            //generate a separate file for each provider
            String fileName = "member_report_" + memberId + ".txt";
            generateMemberReport(memberId, fileName);
        }
	 }
	 
    public void generateMemberReport(String memberId, String filename) throws IOException{
    	//retrieve provider info
    	HashMap<String, String> memberInfo = credentials.getEntry(memberId);
    	
    	
        Path filePath = Path.of(System.getProperty("java.io.tmpdir")).resolve("chocan").resolve("reports");
        
        
        // Create the directory if it doesn't exist
        if (!filePath.toFile().exists()) {
            filePath.toFile().mkdirs();
        }

        // Create the file named 
        filePath = filePath.resolve(filename);

        // Get a file object from the path
        File file = filePath.toFile();
        
        
        // Use try-with-resources to ensure the writer is closed properly
        try (FileWriter fstream = new FileWriter(file); BufferedWriter writer = new BufferedWriter(fstream)) {
            writeDetails(memberId, writer, memberInfo);
            writeServices("memberId",memberId,writer);
            System.out.println("Report written to " + filePath.toString());
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    
    private void writeDetails(String memberId, BufferedWriter writer, HashMap<String, String> memberInfo) throws IOException {
        //formatting provider details
        writer.write("Member Name:" + String.format("%-25s\n", memberInfo.getOrDefault("name", "")));
        //writer.write("Member Number:" + String.format("%-9s\n", serviceInfo.getOrDefault("providerId", "")));
        writer.write("Member Number:" + String.format("%-9s\n", memberId));
        writer.write("Street Address:" + String.format("%-25s\n", memberInfo.getOrDefault("address", "")));
        writer.write("City:" + String.format("%-14s\n", memberInfo.getOrDefault("city", "")));
        writer.write("State:" + String.format("%-2s\n", memberInfo.getOrDefault("state", "")));
        writer.write("ZIP Code:" + String.format("%-5s\n", memberInfo.getOrDefault("zipcode", "")));
        writer.write("\n");
    }
    
    private void writeServices(String field, String matchVal, BufferedWriter writer) throws IOException {
        //use match search to get services
        HashMap<String, HashMap<String, String>> matchedRecords = services.matchSearch(field, matchVal);

        // Iterate through the results and print them
        for (String key : matchedRecords.keySet()) {
        	writer.write("-----------------------\n");
            HashMap<String, String> record = matchedRecords.get(key);
            //System.out.println("Record Key: " + key);
            for (String recordField : record.keySet()) {
					writer.write(recordField + ": " + String.format("%-20s\n", record.get(recordField)));
            }
        }
    }


public static void main(String[] args) {
	
    // Initialize your database classes (adjust this with actual constructors or methods)
    CredentialsDatabase credentialsDatabase = new CredentialsDatabase();
    ServiceDatabase serviceDatabase = new ServiceDatabase();
    
    credentialsDatabase.addEntry("1010", createMockMemberInfo());
    serviceDatabase.addEntry("1010", createMockServiceInfo());
    
    // Initialize the controller with the databases
    MemberReportController controller = new MemberReportController(credentialsDatabase, serviceDatabase);

    // Generate reports for the providers
    try {
        List<String> memberIds = Arrays.asList("1010"); // Add more IDs as needed
        controller.generateMemberReports(memberIds);
        
        // If no exception is thrown, and files are created, the method works
        System.out.println("Reports generated successfully.");
    } catch (IOException e) {
        // If there's an IOException, it will be printed to the console
        e.printStackTrace();
    }
    credentialsDatabase.removeEntry("1010");
    serviceDatabase.removeEntry("1010");
}

// Mock method to create provider information
private static HashMap<String, String> createMockMemberInfo() {
    HashMap<String, String> providerInfo = new HashMap<>();
    providerInfo.put("name", "Test Member");
    providerInfo.put("password", "doglover");
    providerInfo.put("role", "member");
    providerInfo.put("address", "123 Test Street");
    providerInfo.put("zipcode", "12345");
    providerInfo.put("state", "TS");
    return providerInfo;
}

// Mock method to create service information
private static HashMap<String, String> createMockServiceInfo() {
    HashMap<String, String> serviceInfo = new HashMap<>();
    serviceInfo.put("dateOfService", "2023-01-01");
    serviceInfo.put("memberId", "1010");
    serviceInfo.put("serviceCode", "service1");
    serviceInfo.put("fee", "100.00");
    serviceInfo.put("providerId", "2020");
    return serviceInfo;
}
}

