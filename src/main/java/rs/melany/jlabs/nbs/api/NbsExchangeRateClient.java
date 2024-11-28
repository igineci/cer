/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.melany.jlabs.nbs.api;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;

import rs.melany.jlabs.nbs.handler.HeaderHandlerResolver;
import rs.melany.jlabs.nbs.results.ExchangeRate;
import rs.melany.jlabs.nbs.results.ExchangeRatesByDate;
import rs.nbs.communicationoffice.AuthenticationHeader;
import rs.nbs.communicationoffice.ExchangeRateXmlService;
import rs.nbs.communicationoffice.ExchangeRateXmlServiceSoap;

/**
 * A utility class for interacting with the National Bank of Serbia's Exchange Rate Web Service.
 *
 * Provides methods for authenticating, retrieving exchange rate data and parsing XML responses.
 *
 * NOTE: Authentication credentials are loaded from environment variables for security purposes.
 *
 * @author andjela.djekic
 */
public class NbsExchangeRateClient {

    /**
     * Initializes and returns the ExchangeRateXmlService with the necessary authentication headers.
     *
     * @return an instance of ExchangeRateXmlService with authentication headers set.
     */
    public ExchangeRateXmlService initializeService() {

        // Create a service instance for communication with the NBS Web Service
        ExchangeRateXmlService service = new ExchangeRateXmlService();

        // Prepare authentication credentials (retrieved from the environment variables)
        AuthenticationHeader ah = new AuthenticationHeader();
        ah.setLicenceID("NBS_LICENCE_ID");
        ah.setPassword("NBS_PASSWORD");
        ah.setUserName("NBS_USERNAME");

        // Preparing a handler to modify the SOAP header by inserting access credentials.
        HeaderHandlerResolver handlerResolver = new HeaderHandlerResolver(ah);

        // Binding the Web Service to the "handler" object (modifier) that will implement the modification when needed.
        service.setHandlerResolver(handlerResolver);

        return service;

    }

    /**
     * Retrieves an exchange rate list by its number, year and type.
     *
     * @param listNumber the unique number of exhange rate list
     * @param year       the year of the list
     * @param type       the type of exchange rate (for example average)
     * @return an ExchangeRate object containing the parsed data or null if an error occurs
     */
    public ExchangeRate getExchangeRateByListNumber(int listNumber, int year, int type) {

        // Opening port through which the available functions of the Web Service are exposed - Fetching the SOAP service instance
        ExchangeRateXmlServiceSoap serviceSoap = this.initializeService().getExchangeRateXmlServiceSoap12();

        try {
            // Call the SOAP method and retrieve the XML response as a string
            String responseXml = serviceSoap.getExchangeRateByListNumber(listNumber, year, type);

            // Parse the XML into an ExchangeRate object
            return parseXml(responseXml, ExchangeRate.class);

        } catch (Exception ex) {
            // Log the exception with a meaningful message
            System.out.println("Error parsing exchange rate data: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }


    /**
     * Retrieves an exchange rate by date and type.
     *
     * @param type type of the exchange rate (for example average)
     * @param date the specific date for the exchange rate in yyyy-MM-dd format
     * @return an ERbyDate object containing the parsed data or null if an error occurs
     */
    public ExchangeRatesByDate getExchangeRateByDate(int type, String date) {

        // Opening port through which the available functions of the Web Service are exposed - Fetching the SOAP service instance
        ExchangeRateXmlServiceSoap serviceSoap = this.initializeService().getExchangeRateXmlServiceSoap12();

        try {
            // Call the SOAP method and retrieve the XML response as a string
            String responseXml = serviceSoap.getExchangeRateByDate(date, type);
            // Parse the XML into an ERbyDate object
            return parseXml(responseXml, ExchangeRatesByDate.class);

        } catch (Exception ex) {
            System.out.println("Error retrieving exchange rate by date: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * A generic method to parse an XML string into a Java object using JAXB.
     *
     * @param <T>   the type of the Java object to parse into
     * @param xml   the XML string to parse
     * @param clazz the class of the Java object
     * @return the parsed Java object or null if an error occurs
     */
    private <T> T parseXml(String xml, Class<T> clazz) {
        // Preparation for parsing XML String into an object via the JAXB.Binding mechanism
        // The mechanism requires the use of a Stream object to work, so we wrap the string in a memory stream object.
        try (StringReader reader = new StringReader(xml)) {
            // Instantiate a JAXB Binding context by passing it the definition of the class to be deserialized
            JAXBContext context = JAXBContext.newInstance(clazz);
            // Deserialization of data from XML into an object (so-called "unmarshalling")
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // Returning the parsed object to the caller.
            return clazz.cast(unmarshaller.unmarshal(reader));
        } catch (JAXBException ex) {
            System.out.println("Error parsing XML to: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
