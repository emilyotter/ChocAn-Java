package chocan.database;

import java.util.HashMap;

public class CredentialsDatabase extends AbstractDatabase{


    // Mandatory fields:
    protected String[] mandatoryFields = {"name", "password", "role", "address", "zipcode", "state"};
    
    // Role options:
    protected String[] roleOptions = {"member", "provider", "operator", "manager"};

    // Constructor
    public CredentialsDatabase() {
        super("credentials");
    }


   
    /**
     * Checks that the format of a record is valid.
     * @param data the record to be checked
     * @throws IllegalArgumentException if the format is invalid
     */
    private void checkFormat(HashMap<String, String> data) throws IllegalArgumentException {
        
        // Check that all mandatory fields are present
        for (String field : mandatoryFields) {
            if (!data.containsKey(field)) {
                throw new IllegalArgumentException("Missing mandatory field: " + field);
            }
        }

    
        // Check that the role is valid
        boolean validRole = false;
        for (String role : roleOptions) {
            if (data.get("role").equals(role)) {
                validRole = true;
                break;
            }
        }
        if (!validRole) {
            throw new IllegalArgumentException("Invalid role: " + data.get("role"));
        }


        // Additional checks can be added here
        // FILL ME

        return;
    }
    
    /**
     * Checks if a user id is already in use.
     * @param id the id to be checked
     * @return true if the id is already in use, false otherwise
     */
    private boolean checkIdClash(String id){
        return recordExists(id);
    }

    /**
     * Add a new record to the database.
     *
     * @param id    The key for the new record.
     * @param record The record to be added.
     */
    public boolean addCredentials(String id, HashMap<String, String> record) {
        if (checkIdClash(id)) {
            return false;
        }
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

    /**
     * Deletes all records in the database
     */
    @Override
    public void delete(){
        super.delete();
    }



}