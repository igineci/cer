/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.melany.jlabs.nbs.results;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;


/**
 * This class represents a collection of exchange rates for a specific date, as received from the National Bank of Serbia service.
 * The data is deserialized from XML into a list of ExchangeRateByDate objects.
 *
 * @author andjela.djekic
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ExchangeRateDataSet", namespace = "")
public class ExchangeRatesByDate {

    /**
     * List of exchange rates for the specified date.
     * Each item in the list represents an exchange rate record.
     */
    @XmlElement(name = "ExchangeRate", namespace = "")
    public List<ExchangeRateByDate> exchangeRatesByDate;

    /**
     * Gets the list of exchange rates for the specified date.
     *
     * @return A list of ExchangeRateByDate objects.
     */
    public List<ExchangeRateByDate> getExchangeRatesByDate() {
        return exchangeRatesByDate;
    }

}
