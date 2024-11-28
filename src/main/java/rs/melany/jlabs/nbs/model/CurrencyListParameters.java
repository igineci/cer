package rs.melany.jlabs.nbs.model;

/**
 * Class representing configuration parameters used for currency lists retrieving.
 * This class stores the maximum list number and year range for database queries.
 *
 * @author andjela.djekic
 */
public class CurrencyListParameters {

    /**
     * The maximum list number that can be retrieved from the database.
     */
    private int maxListNumber;

    /**
     * The starting year for the data range.
     */
    private int startYear;

    /**
     * The ending year for the data range.
     */
    private int endYear;


    /**
     * Constructor for initializing the database configuration parameters.
     *
     * @param maxListNumber The maximum list number.
     * @param startYear     The starting year.
     * @param endYear       The ending year.
     */
    public CurrencyListParameters(int maxListNumber, int startYear, int endYear) {
        this.maxListNumber = maxListNumber;
        this.startYear = startYear;
        this.endYear = endYear;
    }


    /**
     * Gets the ending year for the data range.
     *
     * @return the ending year.
     */
    public int getEndYear() {
        return endYear;
    }


    /**
     * Gets the starting year for the data range.
     *
     * @return the starting year.
     */
    public int getStartYear() {
        return startYear;
    }


    /**
     * Gets the maximum list number.
     *
     * @return the maximum list number.
     */
    public int getMaxListNumber() {
        return maxListNumber;
    }


}
