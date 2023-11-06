package test.database;

import chocan.database.CredentialsDatabase;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;

public class CredentialsDatabaseTest {

    @Test
    public void testGetCredentialsExistingRecord() {
        CredentialsDatabase database = new CredentialsDatabase();
        HashMap<String, String> record = new HashMap<>();
        record.put("name", "Bob");
        record.put("password", "bobpass");
        record.put("role", "member");
        database.addCredentials("bobuser", record);

        HashMap<String, String> retrievedRecord = database.getCredentials("bobuser");
        assertNotNull(retrievedRecord);
        assertEquals("Bob", retrievedRecord.get("name"));
        assertEquals("bobpass", retrievedRecord.get("password"));
        assertEquals("member", retrievedRecord.get("role"));
    }

    @Test
    public void testGetCredentialsNonExistingRecord() {
        CredentialsDatabase database = new CredentialsDatabase();
        assertNull(database.getCredentials("nonexistentuser"));
    }

    @Test
    public void testCredentialsExist() {
        CredentialsDatabase database = new CredentialsDatabase();
        HashMap<String, String> record = new HashMap<>();
        record.put("name", "Charlie");
        record.put("password", "charlie123");
        record.put("role", "provider");
        database.addCredentials("charlieuser", record);

        assertTrue(database.credentialsExist("charlieuser"));
        assertFalse(database.credentialsExist("nonexistentuser"));
    }

    @Test
    public void testAuthenticateValidCredentials() {
        CredentialsDatabase database = new CredentialsDatabase();
        HashMap<String, String> record = new HashMap<>();
        record.put("name", "David");
        record.put("password", "davidpass");
        record.put("role", "manager");
        database.addCredentials("daviduser", record);

        assertTrue(database.authenticate("daviduser", "davidpass"));
    }

    @Test
    public void testAuthenticateInvalidCredentials() {
        CredentialsDatabase database = new CredentialsDatabase();
        HashMap<String, String> record = new HashMap<>();
        record.put("name", "Eve");
        record.put("password", "evepass");
        record.put("role", "operator");
        database.addCredentials("eveuser", record);

        assertFalse(database.authenticate("eveuser", "invalidpassword"));
        assertFalse(database.authenticate("nonexistentuser", "password"));
    }

    @Test
    public void testGetRoleExistingUser() {
        CredentialsDatabase database = new CredentialsDatabase();
        HashMap<String, String> record = new HashMap<>();
        record.put("name", "Frank");
        record.put("password", "frankpass");
        record.put("role", "provider");
        database.addCredentials("frankuser", record);

        assertEquals("provider", database.getRole("frankuser"));
    }

    @Test
    public void testGetRoleNonExistingUser() {
        CredentialsDatabase database = new CredentialsDatabase();
        assertNull(database.getRole("nonexistentuser"));
    }





}
