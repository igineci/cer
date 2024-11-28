/**
 * Author: Andjela
 * Date: 25.11.2024.
 */


package rs.melany.jlabs.nbs.repository;

import rs.melany.jlabs.nbs.model.CurrencyListParameters;
import rs.melany.jlabs.nbs.database.DatabaseManager;
import rs.melany.jlabs.nbs.exceptions.CERException;
import rs.melany.jlabs.nbs.exceptions.CERStatus;
import rs.melany.jlabs.nbs.results.ExchangeRateList;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for handling data related to exchange rate lists.
 *
 * @author andjela.djekic
 */

public class ExchangeRateRepository {

    private final DatabaseManager dbManager;

    public ExchangeRateRepository(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }


    /**
     * Retrieves all available types of exchange rate lists from the database.
     *
     * @param connection The database connection to use.
     * @return A list of exchange rate list types.
     * @throws CERException If an error occurs during the operation.
     */
    public List<Integer> getAllTypes(Connection connection) throws CERException {
        List<Integer> types = new ArrayList();
        String query = "SELECT typeid FROM nbscurextype";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                types.add(rs.getInt("typeid"));
            }
        } catch (Exception e) {
            throw new CERException(CERStatus.SC_DATABASE_QUERY_ERROR, "host_name_example", "Error while fetching types: " + e.getMessage());
        }
        return types;
    }


    /**
     * Retrieves all currencies from the database.
     *
     * @param connection The database connection to use.
     * @return A list of currency codes.
     * @throws CERException If an error occurs during the operation.
     */
    public List<Integer> getCurrencies(Connection connection) throws CERException {
        List<Integer> currencies = new ArrayList();
        String query = "SELECT curcode FROM nbscurexcurrs";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                currencies.add(rs.getInt("curcode"));
            }
        } catch (Exception e) {
            throw new CERException(CERStatus.SC_DATABASE_QUERY_ERROR, "host_name_example", "Error while fetching currencies: " + e.getMessage());
        }
        return currencies;
    }

    /**
     * Inserts an exchange rate list into the database.
     *
     * @param connection The database connection to use.
     * @param er         The ExchangeRateList object containing the data to be inserted.
     * @throws CERException If an error occurs during the operation.
     */
    public void insertExchangeRateList(Connection connection, ExchangeRateList er) throws CERException {
        String sql = "INSERT INTO NbsCurExList (listnum, datecreated, datefrom, dateto, typeid, codeiso, codenum, codenumchar, codealfachar, namecyr, namelat, nameeng, countrycyr, countrylat, countryeng, ratebuy, rateavg, ratesell, ratefix, unt) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, er.exchangeRateListNumber);
            stmt.setDate(2, new java.sql.Date(new SimpleDateFormat("dd.MM.yyy").parse(er.CreateDate).getTime()));
            stmt.setDate(3, new java.sql.Date(new SimpleDateFormat("dd.MM.yyyy").parse(er.Date).getTime()));
            stmt.setDate(4, new java.sql.Date(new SimpleDateFormat("dd.MM.yyyy").parse(er.DateTo).getTime()));
            stmt.setInt(5, er.ExchangeRateListTypeID);
            stmt.setInt(6, er.CurrencyCode);
            stmt.setShort(7, er.CurrencyGroupID);
            stmt.setString(8, er.CurrencyCodeNumChar);
            stmt.setString(9, er.CurrencyCodeAlfaChar);
            stmt.setString(10, er.CurrencyNameSerCyrl);
            stmt.setString(11, er.CurrencyNameSerLat);
            stmt.setString(12, er.CurrencyNameEng);
            stmt.setString(13, er.CountryNameSerCyrl);
            stmt.setString(14, er.CountryNameSerLat);
            stmt.setString(15, er.CountryNameEng);
            stmt.setDouble(16, er.BuyingRate);
            stmt.setDouble(17, er.MiddleRate);
            stmt.setDouble(18, er.SellingRate);
            stmt.setDouble(19, er.FixingRate);
            stmt.setInt(20, er.Unit);

            stmt.executeUpdate();
        } catch (Exception e) {
            throw new CERException(CERStatus.SC_DATABASE_QUERY_ERROR, "host_name_example", "Error while fetching currencies: " + e.getMessage());
        }
    }


    /**
     * Retrieves data about the most recently added exchange rate list, including its number and validity years.
     *
     * @param connection The database connection to use.
     * @param type       The type of the exchange rate list.
     * @return A CurrencyListParameters object containing the list number, year from, and year to.
     * @throws CERException If an error occurs during the operation.
     */
    public CurrencyListParameters getLatestExchangeRateData(Connection connection, int type) throws CERException {
        String query = "SELECT * FROM getMaxListnum(?);";
        CurrencyListParameters currencyListParameters = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, type);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int maxListNum = rs.getInt(1);
                    int yearFrom = (int) rs.getDouble(2);
                    int yearTo = (int) rs.getDouble(3);

                    currencyListParameters = new CurrencyListParameters(maxListNum, yearTo, yearFrom);
                }
            }
        } catch (Exception e) {
            throw new CERException(CERStatus.SC_DATABASE_QUERY_ERROR, "host_name_example", "Error while fetching currencies: " + e.getMessage());
        }
        return currencyListParameters;
    }

    /**
     * Updates the expiration date of the most recent exchange rate list in the database.
     *
     * @param connection The database connection to use.
     * @param sql        The SQL query to execute for updating the expiration date.
     * @throws CERException If an error occurs during the update.
     */
    public void updateExchangeRateExpiration(Connection connection, String sql) throws CERException {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate(); // Execute update, instead of executeQuery
        } catch (Exception e) {
            throw new CERException(CERStatus.SC_DATABASE_QUERY_ERROR, "host_name_example", "Error while fetching currencies: " + e.getMessage());
        }
    }


}
