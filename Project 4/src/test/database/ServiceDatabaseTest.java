package test.database;

import chocan.database.ServiceDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;

public class ServiceDatabaseTest {

    private ServiceDatabase db;

    @Before
    public void setUp() {
        // Set env variable for testing
        System.setProperty("CHOCAN_TEMP_DIR", "/tmp/chocan/test");

        // Create database
        db  = new ServiceDatabase();
        // Delete previous test entries
        db.delete();
    }

    @After
    public void tearDown() {
        // Delete previous test entries
        db.delete();

        // Reset env variable
        System.clearProperty("CHOCAN_TEMP_DIR");
    }


    @Test
    public void testAddValidService() {
        HashMap<String, String> data = new HashMap<>();
        data.put("serviceCode", "S123");
        data.put("dateOfService", "2023-11-08");
        data.put("memberId", "M456");
        data.put("providerId", "P789");

        try {
            db.addEntry("service1", data);
        } catch (IllegalArgumentException e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddServiceMissingMandatoryField() {
        HashMap<String, String> data = new HashMap<>();
        data.put("serviceCode", "S123");
        data.put("dateOfService", "2023-11-08");
        data.put("memberId", "M456");
        // Missing "providerId" field

        db.addEntry("service2", data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddServiceEmptyMandatoryField() {
        HashMap<String, String> data = new HashMap<>();
        data.put("serviceCode", "S123");
        data.put("dateOfService", "");
        data.put("memberId", "M456");
        data.put("providerId", "P789");

        db.addEntry("service3", data);
    }

    @Test
    public void testAddValidServiceWithDifferentDateFormat() {
        HashMap<String, String> data = new HashMap<>();
        data.put("serviceCode", "S123");
        data.put("dateOfService", "11/08/2023"); // Different date format
        data.put("memberId", "M456");
        data.put("providerId", "P789");

        try {
            db.addEntry("service4", data);
        } catch (IllegalArgumentException e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test
    public void testDeleteDatabase() {
        db.delete();
    }

    @Test
    public void testUpdateService() {
        HashMap<String, String> data = new HashMap<>();
        data.put("serviceCode", "S123");
        data.put("dateOfService", "2023-11-08");
        data.put("memberId", "M456");
        data.put("providerId", "P789");

        db.addEntry("service5", data);

        HashMap<String, String> newData = new HashMap<>();
        newData.put("serviceCode", "S124");

        try {
            db.updateEntry("service5", newData);
        } catch (IllegalArgumentException e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateServiceWithInvalidField() {
        HashMap<String, String> data = new HashMap<>();
        data.put("serviceCode", "S123");
        data.put("dateOfService", "2023-11-08");
        data.put("memberId", "M456");
        data.put("providerId", "P789");

        db.addEntry("service6", data);

        HashMap<String, String> newData = new HashMap<>();
        newData.put("serviceCode", "S124");
        newData.put("invalidField", "value");

        db.updateEntry("service6", newData);
    }

    @Test
    public void testGetEntry() {
        HashMap<String, String> data = new HashMap<>();
        data.put("serviceCode", "S123");
        data.put("dateOfService", "2023-11-08");
        data.put("memberId", "M456");
        data.put("providerId", "P789");

        db.addEntry("service7", data);

        HashMap<String, String> retrievedData = db.getEntry("service7");
        assertNotNull(retrievedData);
        assertEquals("S123", retrievedData.get("serviceCode"));
        assertEquals("2023-11-08", retrievedData.get("dateOfService"));
    }

    @Test
    public void testEntryExists() {
        HashMap<String, String> data = new HashMap<>();
        data.put("serviceCode", "S123");
        data.put("dateOfService", "2023-11-08");
        data.put("memberId", "M456");
        data.put("providerId", "P789");

        db.addEntry("service8", data);

        assertTrue(db.entryExists("service8"));
    }

    @Test
    public void testGetEntryCount() {
        assertEquals(0, db.getEntryCount());

        HashMap<String, String> data = new HashMap<>();
        data.put("serviceCode", "S123");
        data.put("dateOfService", "2023-11-08");
        data.put("memberId", "M456");
        data.put("providerId", "P789");

        db.addEntry("service9", data);

        assertEquals(1, db.getEntryCount());
    }

    
}
