/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package rs.melany.jlabs.nbs;

import java.sql.Connection;

import rs.melany.jlabs.nbs.database.DatabaseManager;
import rs.melany.jlabs.nbs.exceptions.CERException;
import rs.melany.jlabs.nbs.service.ExchangeRateService;
import rs.melany.jlabs.nbs.utility.CommandLineParserUtil;

/**
 * Starter class for downloading exchange rates from the National Bank of Serbia service
 *
 * @author andjela.djekic
 */
public class MainApp {

    public static void main(String[] args) {

        try {
            // Parsing command line arguments and establishing a connection with the database
            DatabaseManager databaseManager = initDatabase(args);

            // Starting the logic of proccesing exchange rates
            processExchangeRates(databaseManager);

        } catch (CERException e) {
            System.err.println("A CERException occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void processExchangeRates(DatabaseManager dbManager) {
        try (Connection connection = dbManager.connect()) {
            ExchangeRateService exchangeRateService = new ExchangeRateService(dbManager);
            exchangeRateService.processExchangeRates(connection);

        } catch (Exception e) {
            System.out.println("Error while processing exchange rates" + e.getMessage());
            e.printStackTrace();
        }
    }

    private static DatabaseManager initDatabase(String[] args) throws CERException {
        DatabaseManager databaseManager = new DatabaseManager(CommandLineParserUtil.parseCommandLineArguments(args));
        databaseManager.connect();
        return databaseManager;
    }


}
