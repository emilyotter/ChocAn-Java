package chocan.database;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public abstract class KeyValDatabase extends AbstractDatabase {

    // Mandatory fields:
    protected String[] mandatoryFields;

    // Constructor
    public KeyValDatabase(String filename) {
        super(filename);
        this.mandatoryFields = setMandatoryFields();
    }

    // Subclasses must implement this method to set mandatory fields
    public abstract String[] setMandatoryFields();

    // Subclasses must implement this method to check the format of a record
    public abstract boolean checkFormat(HashMap<String, String> data) throws IllegalArgumentException;

       
    /**
     * Checks if a record with the given ID already exists in the database.
     * @param id the ID to check for
     * @return true if a record with the given ID exists, false otherwise
     */
    protected boolean checkKeyClash(String id){
        return recordExists(id);
    }
    
    /**
     * Adds a record to the database.
     * @param key the key of the record
     * @param data the data of the record
     */
    public void addEntry(String key , HashMap<String, String> data) throws IllegalArgumentException {
        // Check key clash
        if (checkKeyClash(key)){
            throw new IllegalArgumentException("Key already in use: " + key);
        }
        
        // Check that the format is valid
        if (!checkFormat(data)) {
            throw new IllegalArgumentException("Invalid format");
        }
        
        // Check that the key is not already in use
        if (checkKeyClash(key)) {
            throw new IllegalArgumentException("Key already in use: " + key);
        }
        
        // Add the record
        addRecord(key, data);
    }

    /**
     * Removes a record from the database.
     * @param key the key of the record to remove
     * @throws IllegalArgumentException if the key is not found in the database
     */
    public void removeEntry(String key) throws IllegalArgumentException {
        // Check that the key is valid
        if (!checkKeyClash(key)) {
            throw new IllegalArgumentException("Key not found: " + key);
        }
        
        // Remove the record
        removeRecord(key);
    }


    /**
     * Updates a record in the database.
     * @param key the key of the record to update
     * @param data the new data for the record
     * @throws IllegalArgumentException if the key is not found in the database
     */
    public void updateEntry(String key, HashMap<String, String> data) throws IllegalArgumentException {
        // Check that the key is valid
        if (!checkKeyClash(key)) {
            throw new IllegalArgumentException("Key not found: " + key);
        }
        
        // Check that the every key is one of the mandatory fields
        for (String field : data.keySet()) {
            if (!Arrays.asList(mandatoryFields).contains(field)) {
                throw new IllegalArgumentException("Invalid field: " + field);
            }
        }
        
        // Update the record
        updateRecord(key, data);
    }

    /**
     * Checks if a record with the given key exists in the database.
     * @param key the key to check for
     * @return true if a record with the given key exists, false otherwise
     */
    public boolean entryExists(String key) {
        return recordExists(key);
    }


    /**
     * Returns the number of entries in the database.
     * @return the number of entries in the database
     */
    public int getEntryCount() {
        return getRecordCount();
    }


    /**
     * Returns the data of the entry with the given key.
     * @param key the key of the entry to retrieve
     * @return the data of the entry with the given key
     * @throws IllegalArgumentException if the key is not found in the database
     */
    public HashMap<String, String> getEntry(String key) throws IllegalArgumentException {
        // Check that the key is valid
        if (!checkKeyClash(key)) {
            throw new IllegalArgumentException("Key not found: " + key);
        }
        
        // Return the record
        return getRecord(key);
    }

    /**
     * Prints all entries in the database with their mandatory fields.
     */
    public void printAllEntries() {
        // Print header row
        System.out.println("All entries in " + this.filePath.getFileName() + ":");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-15s  ", "Key");
        for (String field : mandatoryFields) {
            System.out.printf("%-15s  ", field);
        }
        System.out.println();
    
        // Print data rows
        for (Object key : getAllRecords().keySet()) {
            System.out.printf("%-15s  ", key);
            Map<String, String> record = getAllRecords().get(key);
    
            for (String field : mandatoryFields) {
                System.out.printf("%-15s  ", record.get(field));
            }
    
            System.out.println();
        }
    
        System.out.println("-------------------------------------------------------------");
    }
    

    /**
     * Deletes all records in the database
     */
    @Override
    public void delete(){
        super.delete();
    }
    

}
