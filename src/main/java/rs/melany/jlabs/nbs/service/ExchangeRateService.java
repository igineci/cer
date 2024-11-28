package rs.melany.jlabs.nbs.service;

import rs.melany.jlabs.nbs.api.NbsExchangeRateClient;
import rs.melany.jlabs.nbs.database.DatabaseManager;
import rs.melany.jlabs.nbs.model.CurrencyListParameters;
import rs.melany.jlabs.nbs.repository.ExchangeRateRepository;
import rs.melany.jlabs.nbs.results.ExchangeRate;
import rs.melany.jlabs.nbs.results.ExchangeRateList;
import rs.melany.jlabs.nbs.results.ExchangeRatesByDate;
import rs.melany.jlabs.nbs.utility.HelperFunctions;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Service class responsible for fetching and processing exchange rate lists
 * from the National Bank of Serbia (NBS). This includes fetching data,
 * updating the database, and handling date transitions (e.g., New Year's).
 *
 * @author andjela.djekic
 */
public class ExchangeRateService {
    private final DatabaseManager databaseManager;
    private final NbsExchangeRateClient nbsClient;

    /**
     * Constructor for initializing the service with the necessary dependencies.
     *
     * @param databaseManager Database manager for managing connections.
     */
    public ExchangeRateService(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        this.nbsClient = new NbsExchangeRateClient();
    }

    /**
     * Processes all exchange rates by iterating through each type of exchange
     * rate list and performing necessary database updates.
     *
     * @param connection Database connection.
     * @throws Exception If any error occurs during processing.
     */
    public void processExchangeRates(Connection connection) throws Exception {
        ExchangeRateRepository exchangeRateRepository = new ExchangeRateRepository(databaseManager);

        // Retrieve all types of exchange rate lists and available currencies from the database.
        List<Integer> types = exchangeRateRepository.getAllTypes(connection);
        List<Integer> currencies = exchangeRateRepository.getCurrencies(connection);

        // Processing exchange rates for each type.
        for (int type : types) {
            processExchangeRateForType(connection, type, currencies, exchangeRateRepository);
        }
    }

    /**
     * Processes exchange rate data for a specific type by fetching data from
     * NBS, updating the database, and handling year transitions if necessary.
     *
     * @param connection      Database connection.
     * @param type            The type of exchange rate list to process.
     * @param validCurrencies List of available currencies for validation.
     * @param repository      Repository for performing database operations.
     * @throws Exception If any error occurs during processing.
     */
    private void processExchangeRateForType(Connection connection, int type, List<Integer> validCurrencies, ExchangeRateRepository repository) throws Exception {
        try {
            // Retrieve the latest exchange rate data from the database (list number and year).
            CurrencyListParameters latestParameters = repository.getLatestExchangeRateData(connection, type);

            int listStartNumber;
            String dateForList;
            int yearForList;
            boolean happyNewYear = false;
            int lastListNumberInDatabase = latestParameters != null ? latestParameters.getMaxListNumber() : 1;

            if (latestParameters == null) {
                // If no prior data exists, initialize with the current date.
                Date currentDate = new Date();
                dateForList = HelperFunctions.formatDateToString(currentDate, "yyyyMMdd");
                yearForList = LocalDate.now().getYear();
                listStartNumber = 1;

            } else {
                // Retrieve details of the last recorded exchange rate list.
                lastListNumberInDatabase = latestParameters.getMaxListNumber();
                int startYear = latestParameters.getStartYear();

                try {
                    // Fetch the latest exchange rate from NBS based on the last recorded list
                    ExchangeRate latestRate = nbsClient.getExchangeRateByListNumber(lastListNumberInDatabase, startYear, type);
                    // Date for updating the database
                    Date endDateOfLastList = new Date(new SimpleDateFormat("dd.MM.yyyy").parse(latestRate.getExchangeRateList().get(0).DateTo).getTime());

                    //Update the database to close expired lists.
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(endDateOfLastList);
                    // Year of the end of validity of the last entered exchange rate list
                    int endYearOfLastList = calendar.get(Calendar.YEAR);
                    int currentYear = LocalDate.now().getYear();

                    // SQL Query for updating the database
                    String sqlUpdate = "UPDATE NbsCurExList SET dateto = '" + HelperFunctions.toIso8601(endDateOfLastList) + "' WHERE listnum = " + lastListNumberInDatabase + " AND EXTRACT(YEAR FROM datefrom) = " + currentYear + ";";
                    // Closing expired rate
                    repository.updateExchangeRateExpiration(connection, sqlUpdate);

                    // Determine if this is a regular update or a New Year Transition
                    // If this condition is true - it is not a New Year Transition so we can do a regular update
                    if ((endYearOfLastList == startYear && currentYear == endYearOfLastList) || endYearOfLastList == 4172) {
                        dateForList = HelperFunctions.formatDateToString(new Date(), "yyyyMMdd");
                        listStartNumber = lastListNumberInDatabase + 1;
                        yearForList = endYearOfLastList;
                    } else {
                        dateForList = (currentYear - 1) + "1231";
                        // Previous year
                        yearForList = currentYear - 1;
                        listStartNumber = 1;
                        happyNewYear = true;
                    }
                } catch (ParseException e) {
                    System.out.println("Date parsing error for the latest exchange rate list.");
                    e.printStackTrace();
                    return;
                }

            }

            // Fetch and process exchange rate lists for the given type.
            while (true) {
                try {
                    ExchangeRatesByDate ratesByDate = nbsClient.getExchangeRateByDate(type, dateForList);
                    // Number of the exchange rate list
                    int fetchedERListNumber = ratesByDate.getExchangeRatesByDate().get(1).getExchangeRateListNumber();

                    // Process only if there are new exchange rate lists. (if numbers are equal rate is closed in database)
                    if (fetchedERListNumber != lastListNumberInDatabase) {
                        // a loop for each day of the difference
                        for (int currentListNumber = listStartNumber; currentListNumber <= fetchedERListNumber; currentListNumber++) {
                            ExchangeRate currentExchangeRate = nbsClient.getExchangeRateByListNumber(currentListNumber, yearForList, type);
                            for (ExchangeRateList exchangeRateDetails : currentExchangeRate.getExchangeRateList()) {
                                // Validate and insert only supported currencies.
                                if (validCurrencies.contains(exchangeRateDetails.CurrencyCode)) {
                                    // inserting exchange rate data in the database
                                    repository.insertExchangeRateList(connection, exchangeRateDetails);
                                }
                            }
                        }
                    }

                    if (!happyNewYear) {
                        break;
                    } else {
                        dateForList = HelperFunctions.formatDateToString(new Date(), "yyyyMMdd");
                        yearForList = LocalDate.now().getYear();
                        // If its New Year - for the first exchange rate list the number must be 1 - so that is the list start
                        listStartNumber = 1;
                        happyNewYear = false;
                    }
                } catch (Exception e) {
                    System.out.println("Error while processing exchange rate data for type: " + type);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while processing exchange rates for type: " + type);
            e.printStackTrace();
        } finally {
            databaseManager.disconnect();
        }



    }
}
