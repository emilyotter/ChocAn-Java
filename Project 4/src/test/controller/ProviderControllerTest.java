package test.controller;

import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import chocan.controller.ProviderController;
import chocan.database.CredentialsDatabase;
import chocan.database.ServiceDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProviderControllerTest {

    private CredentialsDatabase mockCredentialsDatabase;
    private ServiceDatabase mockServiceDatabase;
    private ProviderController providerController;

    @Before
    public void setUp() {
        mockCredentialsDatabase = new CredentialsDatabase();
        mockServiceDatabase = new ServiceDatabase();
        providerController = new ProviderController(mockCredentialsDatabase, mockServiceDatabase);
    }

    @After
    public void tearDown() {
        mockCredentialsDatabase.delete();
        mockServiceDatabase.delete();
    }

    @Test
    public void testValidateMemberValid() {
        // Arrange
        String memberId = "123456";
        String memberName = "John Doe";
        mockCredentialsDatabase.addEntry(memberId, createMemberData(memberName));

        // Act
        // Redirect system output to capture console output for validation
        String consoleOutput = redirectSystemOut(() -> providerController.validateMember());

        // Assert
        assertTrue(consoleOutput.contains("Member found: " + memberName));
    }

    @Test
    public void testValidateMemberInvalid() {

        // Act
        // Redirect system output to capture console output for validation
        String consoleOutput = redirectSystemOut(() -> providerController.validateMember());

        // Assert
        assertTrue(consoleOutput.contains("Invalid member number."));
    }

    private HashMap<String, String> createMemberData(String name) {
        HashMap<String, String> memberData = new HashMap<>();
        memberData.put("name", name);
        memberData.put("role", "member");
        // Add other necessary member data
        return memberData;
    }

    private String redirectSystemOut(Runnable action) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            action.run();
        } finally {
            System.setOut(originalOut);
        }

        return outputStream.toString();
    }
}

