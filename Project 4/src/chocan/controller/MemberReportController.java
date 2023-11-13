package chocan.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import chocan.database.CredentialsDatabase;
import chocan.database.KeyValDatabase;
import chocan.database.ServiceDatabase;

public class MemberReportController extends AbstractReportController { //optional inheritance

    public static void generateMemberReport(ServiceDatabase serviceDatabase, CredentialsDatabase credentialsDatabase,
            String memberNumber, String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            // Get member information from credentials database
            HashMap<String, String> memberInfo = getMemberInfo(credentialsDatabase, memberNumber);

            writeMemberInfo(writer, memberInfo);

            writer.newLine();
            writer.write("Date\tProvider\tService");
            writer.newLine();

            for (String entryName : serviceDatabase.getAllEntries()) {
                HashMap<String, String> data = serviceDatabase.getEntry(entryName);

                // check if the service is for the specific member
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

    private static HashMap<String, String> getMemberInfo(CredentialsDatabase credentialsDatabase, String memberNumber) {
        // get information from the credentials database using the member number
        return credentialsDatabase.getEntry(memberNumber);
    }

    private static void writeMemberInfo(BufferedWriter writer, HashMap<String, String> memberInfo) throws IOException {
        writer.write("Member Name: " + memberInfo.get("name"));
        writer.newLine();
        writer.write("Member Number: " + memberInfo.get("memberNumber"));
        writer.newLine();
        writer.write("Member Street Address: " + memberInfo.get("address"));
        writer.newLine();
        writer.write("Member City: " + memberInfo.get("city"));
        writer.newLine();
        writer.write("Member State: " + memberInfo.get("state"));
        writer.newLine();
        writer.write("Member ZIP Code: " + memberInfo.get("zipcode"));
        writer.newLine();
    }

    private static String getProviderName(CredentialsDatabase credentialsDatabase, String providerId) {
        // get provider information from the credentials database using the providerId
        HashMap<String, String> providerInfo = credentialsDatabase.getEntry(providerId);
        
        return providerInfo.get("name"); //need to add "name" to provider database
    }
    
    private static String getServiceName(ServiceDatabase serviceDatabase, String serviceCode) {
        // Retrieve service information from the service database using the serviceCode
        HashMap<String, String> serviceInfo = serviceDatabase.getEntry(serviceCode);
    
        return serviceInfo.get("serviceName"); //need to add "name" to provider database
    }

    public static void main(String[] args) {
        ServiceDatabase serviceDb = new ServiceDatabase();
        CredentialsDatabase credentialsDb = new CredentialsDatabase();

        String memberNumber = "123456789"; // fake number (REPLACE)
        String filePath = "member_report.txt";

        generateMemberReport(serviceDb, credentialsDb, memberNumber, filePath);
    }
}
