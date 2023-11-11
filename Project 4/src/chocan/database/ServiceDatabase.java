package chocan.database;

import java.util.HashMap;



public class ServiceDatabase extends KeyValDatabase{

    // Constructor
    public ServiceDatabase() {
        super("services");
    }

    // Override setMandatoryFields() method
    @Override
    public String[] setMandatoryFields() {
        return new String[] {"serviceCode", "dateOfService", "memberId", "providerId"};
    }

    /**
     * Overrides the checkFormat method from the KeyValDatabase class to check the format of data being added to the services database.
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
        return true;

    }
    
}