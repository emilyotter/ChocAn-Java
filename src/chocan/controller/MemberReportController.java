/*
* Authors:
* Name:        Dylan Mather
* CWID:        12267750
* Email:       dmmather@crimson.ua.edu
*
*	Contributors: Emily Otter
*/

package chocan.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.nio.file.Path;

import chocan.database.CredentialsDatabase;
import chocan.database.ServiceDatabase;

public class MemberReportController extends AbstractReportController { //extends AbstractReportController
    
	private CredentialsDatabase credentials;
    private ServiceDatabase services;
   
	public MemberReportController(CredentialsDatabase credentials, ServiceDatabase services) {
    	this.credentials = credentials;
    	this.services = services;
    }
	
  @Override
    public void timedMethod() {
    	HashMap<String, HashMap<String,String>> keys = credentials.getAllEntry();
    	Set<String> keySet = keys.keySet();
    	ArrayList<String> listOfKeys= new ArrayList<String>(keySet);
  		generateMemberReports(listOfKeys);
    }
	
	//generates a report for each member as a separate text file
	public void generateMemberReports(List<String> memberIds) {
        // Iterate over each member ID and generate a report
        for (String memberId : memberIds) {
            //generate a separate file for each provider
            String fileName = "member_report_" + memberId + ".txt";
            generateMemberReport(memberId, fileName);
        }
	 }
	 
    public void generateMemberReport(String memberId, String filename){
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
        
        printToConsole(filePath);
    }
    
    
    private void writeDetails(String memberId, BufferedWriter writer, HashMap<String, String> memberInfo) {
        //formatting provider details
        try {
			writer.write("Member Name:" + String.format("%-25s\n", memberInfo.getOrDefault("name", "No info available.")));
        //writer.write("Member Number:" + String.format("%-9s\n", serviceInfo.getOrDefault("providerId", "")));
        writer.write("Member Number:" + String.format("%-9s\n", memberId));
        writer.write("Street Address:" + String.format("%-25s\n", memberInfo.getOrDefault("address", "No info available.")));
        writer.write("City:" + String.format("%-14s\n", memberInfo.getOrDefault("city", "No info available.")));
        writer.write("State:" + String.format("%-2s\n", memberInfo.getOrDefault("state", "No info available.")));
        writer.write("ZIP Code:" + String.format("%-5s\n", memberInfo.getOrDefault("zipcode", "No info available.")));
        writer.write("\n");
        }
        catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void writeServices(String field, String matchVal, BufferedWriter writer){
        
    	try {
			writer.write("\nServices:\n");
    	
    	//use match search to get services
        HashMap<String, HashMap<String, String>> matchedRecords = services.matchSearch(field, matchVal);
        
        // Iterate through the results and print them
        for (String key : matchedRecords.keySet()) {
        	writer.write("-----------------------\n");
            HashMap<String, String> record = matchedRecords.getOrDefault(key,null);
            //System.out.println("Record Key: " + key);
            for (String recordField : record.keySet()) {
					writer.write(recordField + ": " + String.format("%-20s\n", record.getOrDefault(recordField,"No info available.")));
            }
        }
        writer.write("\n");
    	}
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

}