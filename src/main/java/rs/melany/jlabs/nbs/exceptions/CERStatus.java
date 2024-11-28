
package rs.melany.jlabs.nbs.exceptions;

import lombok.Getter;

/**
 * An enum representin the various error statuses related to the Exchange Rate List.
 * Each status contains login information, error code, message and debug level.
 *
 * @author andjela.djekic
 */
@Getter
public enum CERStatus {

    SC_SERVER_NOT_AVAILABLE("server", "500", "Server not available.", "ERROR"),
    SC_DATABASE_CONNECTION_NOT_AVAILABLE("system", "503", "Parameters for connecting to database are not correct.", "ERROR"),
    SC_AUTHENTICATION_FAILED("auth", "401", "Authentication failed.", "ERROR"),
    SC_TOKEN_EXPIRED("auth", "401", "Token expired or invalid.", "ERROR"),
    SC_NETWORK_UNAVAILABLE("network", "503", "Network is unavailable.", "ERROR"),
    SC_TIMEOUT("network", "504", "Request timeout.", "ERROR"),
    SC_DATABASE_QUERY_ERROR("database", "500", "Database query execution failed.", "ERROR"),
    SC_DATABASE_CONNECTION_TIMEOUT("database", "504", "Database connection timeout.", "ERROR"),
    SC_FILE_NOT_FOUND("file", "404", "File not found.", "ERROR"),
    SC_FILE_READ_ERROR("file", "500", "Error reading file.", "ERROR"),
    SC_FILE_WRITE_ERROR("file", "500", "Error writing to file.", "ERROR"),
    SC_PERMISSION_DENIED("permissions", "403", "Permission denied.", "ERROR"),
    SC_ACCESS_DENIED("permissions", "403", "Access denied.", "ERROR"),
    SC_INVALID_INPUT("validation", "400", "Invalid input provided.", "ERROR"),
    SC_INVALID_DATA_FORMAT("validation", "400", "Invalid data format.", "ERROR"),
    SC_API_CONNECTION_FAILED("api", "503", "API connection failed.", "ERROR"),
    SC_API_RESPONSE_ERROR("api", "502", "API response error.", "ERROR"),
    SC_RESOURCE_LIMIT_REACHED("system", "503", "Resource limit reached.", "ERROR"),
    SC_MEMORY_OVERLOAD("system", "503", "Memory overload.", "ERROR"),
    SC_APPLICATION_ERROR("application", "500", "Application error occurred.", "ERROR"),
    SC_CONFIGURATION_ERROR("configuration", "500", "Configuration error.", "ERROR"),
    SC_SYSTEM_UNAVAILABLE("system", "503", "System unavailable.", "ERROR"),
    SC_SERVICE_UNAVAILABLE("service", "503", "Service unavailable.", "ERROR"),
    AUTHENTICATION_ERROR("authentication", "401", "Authentication failed. Invalid credentials.", "ERROR");

    private final String log_name; // Name of the log where the error is recorded
    private final String evt_code; // Error code
    private final String evt_msg; // Error message
    private final String dbg_lvl; // Debugging level

    CERStatus(String log_name, String evt_code, String evt_msg, String dbg_lvl) {
        this.log_name = log_name;
        this.evt_code = evt_code;
        this.evt_msg = evt_msg;
        this.dbg_lvl = dbg_lvl;

    }

    @Override
    public String toString() {
        return "CERStatus{" + "log_name=" + log_name + ", evt_code=" + evt_code + ", evt_msg=" + evt_msg + ", dbg_lvl=" + dbg_lvl + '}';
    }
}
