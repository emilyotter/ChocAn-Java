package test.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MemberReportControllerTest {

    @Test
    public void testWriteDetails() throws IOException {
        // Arrange
        MemberReportController memberReportController = new MemberReportController

        // Create a StringWriter to capture the output
        StringWriter stringWriter = new StringWriter();
        BufferedWriter fakeWriter = new BufferedWriter(stringWriter);

        HashMap<String, String> fakeMemberInfo = new HashMap<>();
        fakeMemberInfo.put("name", "John Doe");
        fakeMemberInfo.put("address", "123 Main St");
        fakeMemberInfo.put("city", "Anytown");
        fakeMemberInfo.put("state", "CA");
        fakeMemberInfo.put("zipcode", "12345");

        // Act
        memberReportController.writeDetails("123456", fakeWriter, fakeMemberInfo);

        // Assert
        String expectedOutput = "Member Name:John Doe                \n" +
                                "Member Number:123456             \n" +
                                "Street Address:123 Main St        \n" +
                                "City:Anytown                     \n" +
                                "State:CA                          \n" +
                                "ZIP Code:12345                    \n\n";

        assertEquals(expectedOutput, stringWriter.toString());
    }
}



