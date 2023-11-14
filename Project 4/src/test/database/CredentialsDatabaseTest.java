package test.database;

import chocan.database.CredentialsDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.nio.file.Path;
import java.util.HashMap;

public class CredentialsDatabaseTest {

    @Before
    public void setUp() {

        // Redirect database to temp directory
        String tempDir =  Path.of(System.getProperty("java.io.tmpdir")).resolve("chocan").resolve("test").toString();
        
        // Set env variable for testing
        System.setProperty("CHOCAN_TEMP_DIR", tempDir);

        CredentialsDatabase db = new CredentialsDatabase();
        // Delete previous test entries if they exist
        db.delete();
    }

    @After
    public void tearDown() {
        CredentialsDatabase db = new CredentialsDatabase();
        // Delete test entries
        db.delete();
        
        // Reset env variable
        System.clearProperty("CHOCAN_TEMP_DIR");
    }

    @Test
    public void testGenerateID() {
        CredentialsDatabase db = new CredentialsDatabase();
        db.generateUniqueID();
    }

    @Test
    public void testAddValidMember() {
        CredentialsDatabase db = new CredentialsDatabase();
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "John Doe");
        data.put("password", "password123");
        data.put("role", "member");
        data.put("address", "123 Main St");
        data.put("zipcode", "12345");
        data.put("state", "CA");

        try {
            db.addEntry("member123_test1", data);
        } catch (IllegalArgumentException e) {
            fail("Unexpected exception: " + e);
        }

        // Delete after each add to avoid duplicate key errors
        db.removeEntry("member123_test1");
    }

    

    @Test(expected = IllegalArgumentException.class)
    public void testAddMemberEmptyMandatoryField() {
        CredentialsDatabase db = new CredentialsDatabase();
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "John Doe");
        data.put("password", "password123");
        data.put("role", "member");
        data.put("address", ""); // Empty "address" field

        db.addEntry("member123_test3", data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMemberInvalidRole() {
        CredentialsDatabase db = new CredentialsDatabase();
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "John Doe");
        data.put("password", "password123");
        data.put("role", "guest"); // Invalid role

        db.addEntry("member123_test4", data);
    }

    @Test
    public void testAuthenticateValidMemberCredentials() {
        CredentialsDatabase db = new CredentialsDatabase();
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "John Doe");
        data.put("password", "password123");
        data.put("role", "member");
        data.put("address", "123 Main St");
        data.put("zipcode", "12345");
        data.put("state", "CA");
        db.addEntry("member123_test5", data);

        assertTrue(db.authenticateCredentials("member123_test5", "password123"));
    }

    @Test
    public void testAuthenticateInvalidMemberCredentials() {
        CredentialsDatabase db = new CredentialsDatabase();
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "John Doe");
        data.put("password", "password123");
        data.put("role", "member");
        data.put("address", "123 Main St");
        data.put("zipcode", "12345");
        data.put("state", "CA");
        db.addEntry("member123_test6", data);

        assertFalse(db.authenticateCredentials("member123_test6", "wrongpassword"));
    }

    @Test
    public void testGetRoleOfExistingMember() {
        CredentialsDatabase db = new CredentialsDatabase();
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "John Doe");
        data.put("password", "password123");
        data.put("role", "member");
        data.put("address", "123 Main St");
        data.put("zipcode", "12345");
        data.put("state", "CA");
        db.addEntry("member123_test7", data);

        assertEquals("member", db.getRole("member123_test7"));
    }

    @Test
    public void testGetRoleOfNonExistingMember() {
        CredentialsDatabase db = new CredentialsDatabase();
        assertNull(db.getRole("nonexistingmember_test8"));
    }

    @Test
    public void testUpdateExistingMemberCredentials() {
        CredentialsDatabase db = new CredentialsDatabase();
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "John Doe");
        data.put("password", "password123");
        data.put("role", "member");
        data.put("address", "123 Main St");
        data.put("zipcode", "12345");
        data.put("state", "CA");
        db.addEntry("member123_test9", data);

        HashMap<String, String> newData = new HashMap<>();
        newData.put("name", "Jane Smith");
        newData.put("password", "newpassword");
        newData.put("address", "456 Elm St");

        db.updateEntry("member123_test9", newData);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNonExistingMemberCredentials() {
        CredentialsDatabase db = new CredentialsDatabase();
        HashMap<String, String> newData = new HashMap<>();
        newData.put("name", "Jane Smith");
        newData.put("password", "newpassword");
        newData.put("address", "456 Elm St");

        db.updateEntry("nonexistingmember_test10", newData);
    }

    @Test
    public void testRemoveExistingMember() {
        CredentialsDatabase db = new CredentialsDatabase();
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "John Doe");
        data.put("password", "password123");
        data.put("role", "member");
        data.put("address", "123 Main St");
        data.put("zipcode", "12345");
        data.put("state", "CA");
        db.addEntry("member123_test11", data);

        db.removeEntry("member123_test11");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingMember() {
        CredentialsDatabase db = new CredentialsDatabase();
        db.removeEntry("nonexistingmember_test12");
    }

    @Test
    public void testDeleteDatabase(){
        CredentialsDatabase db = new CredentialsDatabase();
        db.delete();
    }
}
