package chocan.database;

import java.util.HashMap;

public class CredentialsDatabase extends KeyValDatabase{

    // Role options:
    protected String[] roleOptions = {"member", "provider", "operator", "manager"};

    // Constructor
    public CredentialsDatabase() {
        super("credentials");
    }

    // Override setMandatoryFields() method
    @Override
    public String[] setMandatoryFields() {
        return new String[]{"name", "password", "role", "address", "zipcode", "state"};
    }



    /**
     * Overrides the checkFormat method from the KeyValDatabase class to check the format of data being added to the credentials database.
     * @param data The data to be checked.
     * @return true if the data is in the correct format, false otherwise.
     * @throws IllegalArgumentException if the data is missing a mandatory field, if a mandatory field is empty, or if the role is invalid.
     */
    @Override
    public boolean checkFormat(HashMap<String, String> data) throws IllegalArgumentException{
        
        // Check that all mandatory fields are present and non-empty
        for (String field : mandatoryFields) {
            if (!data.containsKey(field)) {
                throw new IllegalArgumentException("Missing mandatory field: " + field);
            }

            if (data.get(field).isEmpty()) {
                throw new IllegalArgumentException("Empty mandatory field: " + field);
            }
        }

        // Check that role is valid
        boolean validRole = false;
        for (String role : roleOptions) {
            if (data.get("role").equals(role)) {
                validRole = true;
            }
        }
        if (!validRole) {
            throw new IllegalArgumentException("Invalid role: " + data.get("role"));
        }

        return true;

    }

    /**
     * Authenticate a user by id and password.
     *
     * @param id       The id of the user to authenticate.
     * @param password The password of the user to authenticate.
     * @return True if the user is authenticated, false otherwise.
     */
    public boolean authenticateCredentials(String id, String password) {
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


}