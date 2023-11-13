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
    
    	try (FileWriter writer = new FileWriter(filename)){
    		//write provider details
    		writeDetails(writer, providerInfo, servicesProvided);
    	}
    }
    
    
    /*
     * 
     * FORMATTING NOT CHECKED
     * 
     */
    
    private void writeDetails(FileWriter writer, HashMap<String, String> providerInfo, HashMap<String, String> serviceInfo) throws IOException {
        //formatting provider details
        writer.write("Provider Name:" + String.format("%-25s\n", providerInfo.getOrDefault("name", "")));
        writer.write("Provider Number:" + String.format("%-9s\n", serviceInfo.getOrDefault("providerId", "")));
        writer.write("Street Address:" + String.format("%-25s\n", providerInfo.getOrDefault("address", "")));
        writer.write("City:" + String.format("%-14s\n", providerInfo.getOrDefault("city", "")));
        writer.write("State:" + String.format("%-2s\n", providerInfo.getOrDefault("state", "")));
        writer.write("ZIP Code:" + String.format("%-5s\n", providerInfo.getOrDefault("zipcode", "")));
        
        writer.write("Date of Service:" + String.format("%-12s\n", serviceInfo.getOrDefault("dateOfService", "")));
        writer.write("Member Number:" + String.format("%-25s\n", serviceInfo.getOrDefault("memberId", "")));
        writer.write("Service Code:" + String.format("%-6s\n", serviceInfo.getOrDefault("serviceCode", "")));
        writer.write("Fee to be Paid:" + String.format("%-7s\n", serviceInfo.getOrDefault("fee", "")));
    }
}
