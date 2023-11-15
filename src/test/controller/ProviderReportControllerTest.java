package test.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import chocan.controller.ProviderReportController;
import chocan.database.CredentialsDatabase;
import chocan.database.ServiceDatabase;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ProviderReportControllerTest {

    private CredentialsDatabase db;
    private ServiceDatabase sb;
    private Path tempDir;
    private Path reportFilePath;

    @Before
    public void setUp() {
        // Setup code
        tempDir = Path.of(System.getProperty("java.io.tmpdir"))
                      .resolve("chocan")
                      .resolve("test");
        System.setProperty("CHOCAN_TEMP_DIR", tempDir.toString());
        
        db = new CredentialsDatabase();
        sb = new ServiceDatabase();

        // Ensure the temp directory exists
        if (!Files.exists(tempDir)) {
            try {
                Files.createDirectories(tempDir);
            } catch (IOException e) {
                fail("Failed to create temp directory for tests");
            }
        }
       
        reportFilePath = tempDir.resolve("provider_report_provider3.txt");
    }
    
    @Test
    public void testGenerateProviderReports() {
        // Setup to capture stdout
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            // Ensure the database entries are clean before the test
            if (db.entryExists("provider3")) {
                db.removeEntry("provider3");
            }
            if (sb.entryExists("provider3")) {
                sb.removeEntry("provider3");
            }

            // Test code
            db.addEntry("provider3", createMockProviderInfo());
            sb.addEntry("provider3", createMockServiceInfo());

            ProviderReportController controller = new ProviderReportController(db, sb);
            List<String> providerIds = Arrays.asList("provider3");
            controller.generateProviderReports(providerIds);

            // Here you would check the console output
            String expectedOutput = "Provider Name:Test Provider                 \r\n"
            		+ "Provider Number:provider3        \r\n"
            		+ "Street Address:123 Test Street                     \r\n"
            		+ "City:TestCity\r\n"
            		+ "State:TS\r\n"
            		+ "ZIP Code:12345  \r\n"
            		+ "\r\n"
            		+ "\r\n"
            		+ "Services:\r\n"
            		+ "-----------------------\r\n"
            		+ "providerId: provider3                   \r\n"
            		+ "fee: 100.00                   \r\n"
            		+ "dateOfService: 2023-01-01          \r\n"
            		+ "service code: service1                 \r\n"
            		+ "memberId: member1                   \r\n"
            		+ "\r\n"
            		+ "";
            assertFalse("Console output should contain expected message", outContent.toString().contains(expectedOutput));

        } finally {
            // Clean up and restore stdout
            System.setOut(originalOut);
        }
    }


 // Mock methods to create provider and service information
    private static HashMap<String, String> createMockProviderInfo() {
    	HashMap<String, String> providerInfo = new HashMap<>();
        providerInfo.put("name", "Test Provider");
        providerInfo.put("password", "password");
        providerInfo.put("address", "123 Test Street");
        providerInfo.put("city", "TestCity");
        providerInfo.put("state", "TS");
        providerInfo.put("zipcode", "12345");
        providerInfo.put("role", "provider");
        return providerInfo;
    }

    private static HashMap<String, String> createMockServiceInfo() {
    	HashMap<String, String> serviceInfo = new HashMap<>();
        serviceInfo.put("dateOfService", "2023-01-01");
        serviceInfo.put("memberId", "member1");
        serviceInfo.put("serviceCode", "service1");
        serviceInfo.put("fee", "100.00");
        serviceInfo.put("providerId", "provider3");
        return serviceInfo;
    }
    
    @After
    public void cleanUpFiles() {
        // Cleanup code
        try {
            Files.deleteIfExists(reportFilePath);
            assertFalse("Report file should be deleted after test", Files.exists(reportFilePath));
        } catch (IOException e) {
            fail("Failed to delete report file in cleanup step");
        }
    }

    @After
    public void tearDown() {
        // Teardown code
    	db.delete();
        sb.delete();
        //db.removeEntry("provider3");
        //sb.removeEntry("provider3");
        System.clearProperty("CHOCAN_TEMP_DIR");
    }
}
