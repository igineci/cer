package rs.melany.jlabs.nbs.model;

/**
 * A model class for parsed arguments from the command line, used for connecting to a database.
 * The class stores the database connection details such as host, port, database name, username, and password.
 * It is used to parse and store the connection parameters for later use in establishing a database connection.
 *
 * @author andjela.djekic
 */
public class ParsedArgs {

    /**
     * The host of the database server.
     */
    private String host;

    /**
     * The port number of the database server.
     */
    private int port;
    /**
     * The name of the database to connect to.
     */
    private String database;
    /**
     * The username used for authentication with the database.
     */
    private String username;
    /**
     * The password associated with the username for database access.
     */
    private String password;

    // Getters and Setters

    /**
     * Gets the host of the database server.
     *
     * @return the host of the database server
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the host of the database server.
     *
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Gets the port number of the database server.
     *
     * @return the port number of the database server
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the port number of the database server.
     *
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Gets the name of the database.
     *
     * @return the name of the database
     */
    public String getDatabase() {
        return database;
    }

    /**
     * Sets the name of the database.
     *
     * @param database the database name to set
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     * Gets the username used for database authentication.
     *
     * @return the username for authentication
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for database authentication.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password associated with the username for authentication.
     *
     * @return the password for authentication
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for database authentication.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }


}