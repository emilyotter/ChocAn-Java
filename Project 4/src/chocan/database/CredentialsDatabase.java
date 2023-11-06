package chocan.database;

import java.util.HashMap;

public class CredentialsDatabase extends AbstractDatabase{

    // Constructor
    public CredentialsDatabase() {
        super("credentials");
    }


   
    /**
     * Checks the format of the data to ensure that all required fields are present and that the role is valid.
     * @param data a HashMap containing the data to be checked
     * @throws IllegalArgumentException if any required field is missing or if the role is invalid
     */
    private void checkFormat(HashMap<String, String> data) throws IllegalArgumentException {
        // Check that all required fields are present
        if (!data.containsKey("name") || !data.containsKey("password") || !data.containsKey("role")) {
            throw new IllegalArgumentException("Missing required field(s).");
        }

        // Check that role is valid (member or provider or operator or manager)
        String role = data.get("role");
        if (!role.equals("member") && !role.equals("provider") && !role.equals("operator") && !role.equals("manager")) {
            throw new IllegalArgumentException("Invalid role.");
        }
    }
    

    /**
     * Add a new record to the database.
     *
     * @param id    The key for the new record.
     * @param record The record to be added.
     */
    public boolean addCredentials(String id, HashMap<String, String> record) {
        try {
            checkFormat(record);
            addRecord(id, record);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
   
    /**
     * Remove a record from the database.
     *
     * @param id The key of the record to be removed.
     */
    public boolean removeCredentials(String id) {
        if (recordExists(id)) {
            removeRecord(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get a record from the database by id.
     *
     * @param id The key of the record to retrieve.
     * @return The record associated with the given key.
     */
    public HashMap<String, String> getCredentials(String id) {
        if (recordExists(id)) {
            return getRecord(id);
        } else {
            return null;
        }
    }

    /*
     * Check if a user id exists in the database.
     */
    public boolean credentialsExist(String id) {
        return recordExists(id);
    }

    /**
     * Authenticate a user by id and password.
     *
     * @param id       The id of the user to authenticate.
     * @param password The password of the user to authenticate.
     * @return True if the user is authenticated, false otherwise.
     */
    public boolean authenticate(String id, String password) {
        if (recordExists(id)) {
            HashMap<String, String> record = getRecord(id);
            return record.get("password").equals(password);
        } else {
            return false;
        }
    }

    /**
     * Get the role of a user by id.
     *
     * @param id The id of the user.
     * @return The role of the user.
     */
    public String getRole(String id) {
        if (recordExists(id)) {
            HashMap<String, String> record = getRecord(id);
            return record.get("role");
        } else {
            return null;
        }
    }

    /**
     * Get the number of records in the database.
     *
     * @return The number of records in the database.
     */
    public int getLength() {
        return getRecordCount();
    }

    /*
     * Print all records in the database.
     */
    public void printAll() {
        System.out.println("Credentials Database:");
        for (Object key : getAllRecords().keySet()) {
            HashMap<String, String> record = (HashMap<String, String>) getAllRecords().get(key);
            System.out.println("  " + key + ": " + record);
        }
    }



}