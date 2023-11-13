package chocan.controller;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class MemberReportController extends AbstractReportController {

    public static void generateMemberReport(ServiceDatabase serviceDatabase, String memberNumber, String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            writer.write("Member Number: " + memberNumber);
            writer.newLine();

            writer.newLine();
            writer.write("Date\tProvider\tService");
            writer.newLine();

            for (String entryName : serviceDatabase.getAllEntries()) {
                HashMap<String, String> data = serviceDatabase.getEntry(entryName);

                // Check if the service is for the specified member
                if (data.get("memberId").equals(memberNumber)) {
                    String dateOfService = data.get("dateOfService");
                    String providerName = getProviderName(data.get("providerId"));
                    String serviceName = getServiceName(data.get("serviceCode"));

                    writer.write(dateOfService + "\t" + providerName + "\t" + serviceName);
                    writer.newLine();
                }
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getProviderName(String providerId) {
        return "ProviderName"; // Replace
    }

    private static String getServiceName(String serviceCode) {
        // Implement logic to get service name from serviceCode
        return "ServiceName"; // Replace
    }

    public static void main(String[] args) {
        ServiceDatabase serviceDb = new ServiceDatabase();

        String memberNumber = "123456789"; //fake number (REPLACE)
        String filePath = "member_report.txt";

        generateMemberReport(serviceDb, memberNumber, filePath);
    }
}
