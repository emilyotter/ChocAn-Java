package chocan.controller;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class SummaryReportController extends AbstractReportController { //may not need inheritance

    public static void generateSummaryReport(ServiceDatabase serviceDatabase, String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            writer.write("Provider\tConsultations\tTotal Fee");
            writer.newLine();

            HashMap<String, String> data;
            for (String entryName : serviceDatabase.getAllEntries()) {
                data = serviceDatabase.getEntry(entryName);

                String providerId = data.get("providerId");
                int consultations = 1;  // Assuming each entry represents one service
                double totalFee = Double.parseDouble(data.get("fee")); //need to add "fee" field to service database

                // Write information to the report
                writer.write(providerId + "\t" + consultations + "\t" + totalFee);
                writer.newLine();
            }

            writer.write("Total Providers: " + serviceDatabase.getEntryCount());
            writer.newLine();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ServiceDatabase db = new ServiceDatabase();

        String filePath = "summary_report.txt";

        generateSummaryReport(db, filePath);
    }
}

