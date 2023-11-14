package chocan.controller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import chocan.database.CredentialsDatabase;
import chocan.database.ServiceDatabase;

public class SummaryReportController  { //may not need inheritance

	private ServiceDatabase serviceDatabase;

    public SummaryReportController(ServiceDatabase serviceDatabase) {
    	this.serviceDatabase = serviceDatabase;
    }
	
	public void generateSummaryReport() {
		
		Path filePath = Path.of(System.getProperty("java.io.tmpdir")).resolve("chocan").resolve("reports");
        
        
        // Create the directory if it doesn't exist
        if (!filePath.toFile().exists()) {
            filePath.toFile().mkdirs();
        }

        // Create the file named 
        filePath = filePath.resolve("summary_report.txt");

        // Get a file object from the path
        File file = filePath.toFile();
        
        
        // Use try-with-resources to ensure the writer is closed properly
		try (FileWriter fstream = new FileWriter(file); BufferedWriter writer = new BufferedWriter(fstream)){
            //BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            writer.write("Provider\tConsultations\tTotal Fee");
            writer.newLine();

            HashMap<String, String> data;
            for (String entryName : serviceDatabase.getAllEntries()) {
                data = serviceDatabase.getEntry(entryName);
            
                if (data.containsKey("providerId") && data.containsKey("fee")) {
                    String providerId = data.get("providerId");
                    int consultations = 1;  // Assuming each entry represents one service
                    double totalFee = Double.parseDouble(data.get("fee"));
            
                    // Write information to the report
                    writer.write(providerId + "\t" + consultations + "\t" + totalFee);
                    writer.newLine();
                } else {
                    //log a warning
                    System.out.println("Entry " + entryName + " is missing required fields.");
                }
            }
            writer.write("Total Providers: " + serviceDatabase.getEntryCount());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

