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

    // Temporary Directory as Path object
    protected static final Path TEMP_DIR = Path.of(System.getProperty("java.io.tmpdir"));
    protected static final Path DB_DIR = TEMP_DIR.resolve("chocan");
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
        this.userFileName = userFileName;
        this.filePath = DB_DIR.resolve(userFileName + DB_FILE_EXT);

        // If existsAndEmpty, write an empty JSON object to the file
        if (existsAndEmpty(filePath)) {
            writeToFile(filePath, "{}");
        }
        else if (!filePath.toFile().exists()) {
            // Create parent directories if they do not exist
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

        // Read the database file into a JSONObject
        try {
            FileReader reader = new FileReader(filePath.toString());
            this.jsonFile = (JSONObject) parser.parse(reader);
            reader.close();
        } catch (Exception e) {
            System.err.println("Error reading file: " + filePath.toString());
            e.printStackTrace();
        }
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
