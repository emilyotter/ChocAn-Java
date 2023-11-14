/*
 * Authors:
 * Name:        Nichal Bhattarai
 * CWID:        12088410
 * Email:       nbhattarai@crimson.ua.edu
 *
 */


package chocan.database;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * This abstract class provides a template for creating database objects.
 * It is not intended to be instantiated.
 */
public abstract class AbstractDatabase {
    private static final String DB_DIR_ENV_VAR = "CHOCAN_TEMP_DIR";

    // Temporary Directory as Path object
    protected Path TEMP_DIR;

    protected Path DB_DIR;
    protected static final String DB_FILE_EXT = ".json";

    // Database file names
    protected String userFileName;
    protected Path filePath;

    // Simple JSON file and parser
    public JSONObject jsonFile;
    private JSONParser parser = new JSONParser();


    /**
     * Constructor to create an AbstractDatabase instance.
     *
     * @param userFileName The name of the database file.
     */
    public AbstractDatabase(String userFileName) {
        // Set the file name
        this.userFileName = userFileName;

        
        // if null, set to temp directory
        if (System.getProperty(DB_DIR_ENV_VAR) == null) {
            TEMP_DIR = Path.of(System.getProperty("java.io.tmpdir"));
        }
        else {
            // If not null, append the temp directory to the env variable
            TEMP_DIR = Path.of(System.getProperty(DB_DIR_ENV_VAR));
        }
        System.out.println("TEMP_DIR: " + TEMP_DIR);
        // Set the database directory
        this.DB_DIR = TEMP_DIR.resolve("chocan");

        // Set the file path
        this.filePath = DB_DIR.resolve(userFileName + DB_FILE_EXT);

        // Create the database file if it does not exist or 
        createEmptyJsonOnPath(filePath);
        
        // Read the database file into a JSONObject
        this.jsonFile = readIntoJsonObject(filePath);
    }



    /**
     * This method writes the given data to the specified file path.
     *
     * @param filePath The path of the file to write to.
     * @param data The data to write to the file.
     */
    private void writeToFile(Path filePath , String data) {
        try {
            FileWriter writer = new FileWriter(filePath.toString());
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing file: " + filePath.toString());
            e.printStackTrace();
        }
    }

    /**
     * Check if the database file exists and is empty.
     *
     * @return true if the database file exists and is empty, false otherwise.
     */
    private boolean existsAndEmpty(Path filePath) {
        return filePath.toFile().exists() && filePath.toFile().length() == 0;
    }



    /**
     * This method creates an empty JSON file on the given path if it does not exist.
     *
     * @param filePath The path of the file to create.
     */
    private void createEmptyJsonOnPath(Path filePath) {        
        // If existsAndEmpty, write an empty JSON object to the file
        if (existsAndEmpty(filePath)) {
            writeToFile(filePath, "{}");
        }
        else if (!filePath.toFile().exists()) {
            // Make all parent directories
            filePath.toFile().getParentFile().mkdirs();
            
            // Create the database file
            try{
                filePath.toFile().createNewFile();
            }
            catch (IOException e) {
                System.err.println("Error creating file: " + filePath.toString());
                e.printStackTrace();
            }
            writeToFile(filePath, "{}"); // Write an empty JSON object to the file
        }
    }

    /**
     * This method reads the given file path into a JSONObject.
     *
     * @param filePath The path of the file to read.
     */
    private JSONObject readIntoJsonObject(Path filePath) {
        // Read the database file into a JSONObject
        try {
            FileReader reader = new FileReader(filePath.toString());
            JSONObject jsonFile = (JSONObject) parser.parse(reader);
            reader.close();
            return jsonFile;
        } catch (Exception e) {
            throw new RuntimeException("Error reading file: " + filePath.toString(), e);
        }
    }

    
    public void delete() {
        // Delete the database file
        filePath.toFile().delete();

        // Create an empty JSON file on the given path
        createEmptyJsonOnPath(filePath);

        // Reread the database file into a JSONObject
        this.jsonFile = readIntoJsonObject(filePath);

    }

    /**
     * Save the database file.
     */
    public void save() {
        writeToFile(filePath, jsonFile.toJSONString());
    }


    /**
     * Get the database file as a JSONObject.
     *
     * @return The JSON data in the database file.
     */
    public JSONObject getJsonFile() {
        return jsonFile;
    }

    /**
     * Get the database file path.
     *
     * @return The path to the database file.
     */
    public Path getFilePath() {
        return filePath;
    }

    /**
     * Get the database file name.
     *
     * @return The name of the database file.
     */
    public String getFileName() {
        return userFileName;
    }


    /**
     * Add a new record to the database.
     *
     * @param key    The key for the new record.
     * @param record The record to be added.
     */
    protected void addRecord(String key, HashMap<String, String> record) {
        jsonFile.put(key, record);
        save();
    }

    /**
     * Remove a record from the database.
     *
     * @param key The key of the record to be removed.
     */
    protected void removeRecord(String key) {
        jsonFile.remove(key);
        save();
    }


    /**
     * Update a record in the database.
     *
     * @param key    The key of the record to update.
     * @param record The updated record.
     */
    protected void updateRecord(String key, HashMap<String, String> record) {
        // Get the old record
        HashMap<String, String> oldRecord = (HashMap<String, String>) jsonFile.get(key);

        // Iterate through the old record and replace the old values with the new values
        for (String field : record.keySet()) {
            oldRecord.put(field, record.get(field));
        }
        // Replace the old record with the updated record
        jsonFile.put(key, oldRecord);
    }

    /**
     * Get a record from the database.
     *
     * @param key The key of the record to retrieve.
     * @return The record associated with the given key.
     */
    protected HashMap<String, String> getRecord(String key) {
        return (HashMap<String, String>) jsonFile.get(key);
    }

    /**
     * Get all records from the database.
     *
     * @return A map containing all records in the database.
     */
    protected HashMap<String, HashMap<String, String>> getAllRecords() {
        return (HashMap<String, HashMap<String, String>>) jsonFile;
    }

    /**
     * Get the number of records in the database.
     *
     * @return The number of records in the database.
     */
    protected int getRecordCount() {
        return jsonFile.size();
    }

    /**
     * Check if a record exists in the database.
     *
     * @param key The key to check for existence.
     * @return true if the record exists, false otherwise.
     */
    protected boolean recordExists(String key) {
        return jsonFile.containsKey(key);
    }

    /**
     * Get the values of a specific field from all records as a list.
     *
     * @param valueKey The key of the field to retrieve.
     * @return A list of values from all records for the specified field.
     */
    protected ArrayList<String> getRecordValues(String valueKey) {
        // Iterate through the records and add each value to a list
        ArrayList<String> values = new ArrayList<String>();

        for (Object key : jsonFile.keySet()) {
            HashMap<String, String> record = (HashMap<String, String>) jsonFile.get(key);
            String value = record.get(valueKey);
            if (value != null) {
                values.add(value);
            }
        }
        return values;
    }
}
