/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.melany.jlabs.nbs.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class providing helper methods for date formatting and conversion.
 * This class includes static methods for converting {@link java.util.Date} objects
 * into ISO 8601 formatted strings and other custom formats.
 *
 * @author andjela.djekic
 */
public class HelperFunctions {

    private static final String ISO_8601_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Formats a {@link java.util.Date} object into a string using a custom date format.
     *
     * @param date   the {@link java.util.Date} object to be formatted.
     * @param format the desired date format (e.g., "dd-MM-yyyy").
     * @return a string representation of the date in the specified format.
     * @throws IllegalArgumentException if the date or format is null.
     */
    public static String formatDateToString(Date date, String format) {
        if (date == null || format == null) {
            throw new IllegalArgumentException("Date and format parameters cannot be null.");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * Converts a {@link java.util.Date} object to an ISO 8601 formatted string (yyyy-MM-dd HH:mm:ss).
     * This method is useful for preparing date strings for SQL queries or APIs.
     *
     * @param date the {@link java.util.Date} object to be converted.
     * @return a string representation of the date in ISO 8601 format.
     * @throws IllegalArgumentException if the date parameter is null.
     */
    public static String toIso8601(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("The provided date cannot be null.");
        }
        try {
            return new SimpleDateFormat(ISO_8601_FORMAT).format(date);
        } catch (Exception ex) {
            throw new IllegalStateException("An error occurred while formatting the date to ISO 8601.", ex);
        }
    }


}

