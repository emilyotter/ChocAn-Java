package chocan.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import chocan.database.CredentialsDatabase;
import chocan.database.ServiceDatabase;

public class ProviderReportController extends AbstractReportController{
    
	private CredentialsDatabase credentials;
    private ServiceDatabase services;
	
    @Override
    public void timedMethod() {

    }
   
	public ProviderReportController(CredentialsDatabase credentials, ServiceDatabase services) {
    	this.credentials = credentials;
    	this.services = services;
    }
	
	//generates a report for each provider as a separate text file
	public void generateProviderReports(List<String> providerIds) throws IOException {
        // Iterate over each provider ID and generate a report
        for (String providerId : providerIds) {
            //generate a separate file for each provider
            String fileName = "provider_report_" + providerId + ".txt";
            generateProviderReport(providerId, fileName);
        }
	 }
	 
    public void generateProviderReport(String providerId, String filename) throws IOException{
    	//retrieve provider info
    	HashMap<String, String> providerInfo = credentials.getEntry(providerId);
    	
    	//Retrieve services provided
    	HashMap<String, String> servicesProvided = services.getEntry(providerId);
    	/*
    	 * 
    	 * MIGHT NEED TO MAKE A LIST OF SERVICES IN DATABASE
    	 * 
    	 * 
    	 */
    	
    	try (FileWriter writer = new FileWriter("provider_report.txt")){
    		//write provider details
    		writeProviderDetails(writer,providerInfo);
    		
    		//writer service details
    		writeServiceDetails(writer, servicesProvided);
    	}
    }
    
    
    /*
     * 
     * FORMATTING NOT CHECKED
     * 
     */
    
    private void writeProviderDetails(FileWriter writer, HashMap<String, String> providerData) throws IOException {
        //formatting provider details
        writer.write(String.format("%-25s\n", providerData.getOrDefault("name", "")));
        writer.write(String.format("%-9s\n", providerData.getOrDefault("number", "")));
        writer.write(String.format("%-25s\n", providerData.getOrDefault("address", "")));
        writer.write(String.format("%-14s\n", providerData.getOrDefault("city", "")));
        writer.write(String.format("%-2s\n", providerData.getOrDefault("state", "")));
        writer.write(String.format("%-5s\n", providerData.getOrDefault("zipcode", "")));
    }

    private void writeServiceDetails(FileWriter writer, HashMap<String, String> serviceData) throws IOException {
        // formatting service details
        writer.write(String.format("%-12s\n", serviceData.getOrDefault("dateOfService", "")));
        writer.write(String.format("%-6s\n", serviceData.getOrDefault("serviceCode", "")));
        writer.write(String.format("%-7s\n", serviceData.getOrDefault("fee", "")));
    }
}
