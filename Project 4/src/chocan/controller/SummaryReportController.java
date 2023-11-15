package chocan.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import chocan.database.CredentialsDatabase;
import chocan.database.ServiceDatabase;

public class SummaryReportController extends AbstractReportController { 

    private ServiceDatabase services;
    private CredentialsDatabase userDatabase;

    public SummaryReportController(CredentialsDatabase userDatabase, ServiceDatabase services) {
        this.services = services;
        this.userDatabase = userDatabase;
    }
    
    public void generateSummaryReport() {
        
        Path filePath = Path.of(System.getProperty("java.io.tmpdir")).resolve("chocan").resolve("reports");
        
        // Create the directory if it doesn't exist
        if (!filePath.toFile().exists()) {
            filePath.toFile().mkdirs();
        }

        // Create the file named
        filePath = filePath.resolve("summary_report.txt");
        File file = filePath.toFile();
        
        // Use try-with-resources to ensure the writer is closed properly
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("\nProviders:\n");

            int totalFee = 0;
            int totalCons = 0;
            
            HashMap<String, HashMap<String, String>> matchedRecords = userDatabase.matchSearch("role", "provider");

            // Iterate through the results
            for (String key : matchedRecords.keySet()) {
                HashMap<String, String> userData = userDatabase.getEntry(key);
                writer.write("Provider Name: " + String.format("%-25s", userData.getOrDefault("name", "No info available.")) + "\n");

                HashMap<String, HashMap<String, String>> matchedServices = services.matchSearch("providerId", key);
                int numConsultations = matchedServices.keySet().size();
                writer.write("Number of Consultations: " + String.format("%-25s", numConsultations) + "\n");
                totalCons += numConsultations;
                
                int totalProviderFee = 0;
                for (String number : matchedServices.keySet()) {
                    HashMap<String, String> serviceData = services.getEntry(number);
                    int providerFee = Integer.parseInt(serviceData.getOrDefault("fee", "0"));
                    totalProviderFee += providerFee;
                }
                
                writer.write("Total Fee: " + String.format("%-25s", totalProviderFee) + "\n");
                totalFee += totalProviderFee;
            }
            
            writer.write("\nTotals Sheet:\n");
            writer.write("Total Providers: " + String.format("%-25s", matchedRecords.keySet().size()) + "\n");
            writer.write("Total Consultations: " + String.format("%-25s", totalCons) + "\n");
            writer.write("Total Fees: " + String.format("%-25s", totalFee) + "\n");
        } catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

        printToConsole(filePath);
    }

    @Override
    public void timedMethod() {
        generateSummaryReport();
    }
}
