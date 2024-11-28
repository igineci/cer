/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.melany.jlabs.nbs.results;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Represents a collection of exchange rates returned by the National Bank of Serbia (NBS).
 * This class contains a list of exchange rates for a specific date range.
 * <p>
 * It is used for parsing XML data into Java objects and should conform to the NBS API response format.
 *
 * @author andjela.djekic
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ExchangeRateDataSet", namespace = "")
public class ExchangeRate {

    /**
     * A list of exchange rates associated with a specific date range.
     * Each entry contains detailed information about a specific exchange rate.
     *
     * @see ExchangeRateList
     */
    @XmlElement(name = "ExchangeRate", namespace = "")
    private List<ExchangeRateList> exchangeRateList;

    /**
     * Gets the list of exchange rates.
     *
     * @return a list of {@link ExchangeRateList} objects containing exchange rate details.
     */
    public List<ExchangeRateList> getExchangeRateList() {
        return exchangeRateList;
    }

    /**
     * Sets the list of exchange rates.
     *
     * @param exchangeRateList a list of {@link ExchangeRateList} objects to be set.
     */
    public void setExchangeRateList(List<ExchangeRateList> exchangeRateList) {
        this.exchangeRateList = exchangeRateList;
    }


}
