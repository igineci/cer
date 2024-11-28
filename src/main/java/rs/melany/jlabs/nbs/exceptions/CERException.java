/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.melany.jlabs.nbs.exceptions;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Custom exception class for handling CER (Currency Exchange Rate) related errors.
 * This class stores additional information for logging and tracking the errors.
 * 
 * It is designed to provide more detailed logging information including event source,
 * event message, and debugging level.
 */
@Getter
@AllArgsConstructor
@ToString
public class CERException extends Exception {

    private int rec_id; // Record ID for the error
    private String log_name; // Log name where the error occurred
    private Date log_date; // Date when the error occurred
    private String host_name; // The name of the host where the error occurred.
    private String evt_src; // Event source
    private String evt_code; // Event code representing the error
    private String evt_msg; // Event message with details of the error
    private String dbg_lvl; // Debug level for the error


    /**
     * Constructor for creating CERException using CERStatus object.
     * The CERStatus object is used to set predefined logging information.
     *
     * @param c         The CERStatus object containing log information.
     * @param host_name The name of the host where the error occurred.
     * @param evt_src   The source of the event (e.g., a system or module).
     */
    public CERException(CERStatus c, String host_name, String evt_src) {
        this.rec_id = 0;  // Default value for rec_id (can be updated if necessary)
        this.log_name = c.getLog_name();
        this.log_date = new Date(); // Current date and time when the error occurred
        this.host_name = host_name;
        this.evt_src = evt_src;
        this.evt_msg = c.getEvt_msg();
        this.evt_code = c.getEvt_code();
        this.dbg_lvl = c.getDbg_lvl();

    }


    @Override
    public String toString() {
        return "CERException{" +
                "rec_id=" + rec_id +
                ", log_name='" + log_name + '\'' +
                ", log_date=" + log_date +
                ", host_name='" + host_name + '\'' +
                ", evt_src='" + evt_src + '\'' +
                ", evt_code='" + evt_code + '\'' +
                ", evt_msg='" + evt_msg + '\'' +
                ", dbg_lvl='" + dbg_lvl + '\'' +
                '}';
    }

}
