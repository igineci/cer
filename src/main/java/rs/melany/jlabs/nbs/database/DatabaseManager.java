package rs.melany.jlabs.nbs.database;

import java.sql.*;

import rs.melany.jlabs.nbs.model.ParsedArgs;
import rs.melany.jlabs.nbs.exceptions.CERException;
import rs.melany.jlabs.nbs.exceptions.CERStatus;

/**
 * Utility class for managing database connections and executing queries.
 * Handles connection setup, query execution, and resource management.
 *
 * @author andjela.djekic
 */

public class DatabaseManager {

    private final String host;
    private final int port;
    private final String database;
    private final String user;
    private final String password;
    private Connection connection;

    public DatabaseManager(ParsedArgs parsedargs) {
        this.host = parsedargs.getHost();
        this.port = parsedargs.getPort();
        this.database = parsedargs.getDatabase();
        this.user = parsedargs.getUsername();
        this.password = parsedargs.getPassword();
    }

    /**
     * Establishes a connection to the database.
     *
     * @return the established connection
     * @throws CERException if the connection fails
     */
    public Connection connect() throws CERException {

        String url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection == null)
                throw new CERException(CERStatus.SC_DATABASE_CONNECTION_NOT_AVAILABLE, "localhost", this.getClass().getName());

        } catch (SQLException | CERException e) {
            throw new CERException(CERStatus.SC_DATABASE_CONNECTION_NOT_AVAILABLE, "localhost", this.getClass().toString());
        }
        return connection;
    }

    /**
     * Closes the database connection if it is open.
     *
     * @throws SQLException if an error occurs while closing the connection
     */
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
