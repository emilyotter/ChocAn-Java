package chocan.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This abstract class provides a template for creating database objects.
 * It contains methods for connecting to and disconnecting from a database,
 * creating tables, and reading, updating, and deleting data from tables.
 */

public abstract class AbstractDatabase {
    protected Connection connection;

    /**
     * Connects to the database at the specified URL.
     * @param databaseUrl the URL of the database to connect to
     */
    public void connect(String databaseUrl) {
        try {
            connection = DriverManager.getConnection(databaseUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnects from the database.
     */
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates a table in the database with the specified name and definition.
     * @param tableName the name of the table to create
     * @param tableDefinition the definition of the table to create
     */
    public void createTable(String tableName, String tableDefinition) {
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + tableDefinition + ")";
                statement.execute(createTableSQL);
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads data from the specified table. Neccessary arguments may be provided by subclasses.
     */
    public abstract void read();
    
    /**
     * Updates data in the specified table. Neccessary arguments may be provided by subclasses.
     */
    public abstract void update();

    /**
     * Deletes data from the specified table. Neccessary arguments may be provided by subclasses.
     */
    public abstract void delete();

    /**
     * Returns the connection to the database.
     * @return the connection to the database
     */
    public Connection getConnection() {
        return connection;
    }

}
