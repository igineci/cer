/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.melany.jlabs.nbs.results;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Represents an exchange rate list returned by the NBS GetExchangeRateByListNumber function.
 * This class encapsulates the details of an exchange rate list including exchange rates, currency details, and country names.
 * It is used for mapping the XML structure returned by the NBS API.
 *
 * @author andjela.djekic
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "ExchangeRateListNumber",
        "Date",
        "CreateDate",
        "DateTo",
        "ExchangeRateListTypeID",
        "CurrencyGroupID",
        "CurrencyCode",
        "CurrencyCodeNumChar",
        "CurrencyCodeAlfaChar",
        "CurrencyNameSerCyrl",
        "CurrencyNameSerLat",
        "CurrencyNameEng",
        "CountryNameSerCyrl",
        "CountryNameSerLat",
        "CountryNameEng",
        "Unit",
        "BuyingRate",
        "MiddleRate",
        "SellingRate",
        "FixingRate"
})
public class ExchangeRateList {

    /**
     * The unique identifier for the exchange rate list.
     */
    @XmlElement(name = "ExchangeRateListNumber", namespace = "")
    public int exchangeRateListNumber;

    @XmlElement(name = "Date", namespace = "")
    public String Date;

    @XmlElement(name = "CreateDate", namespace = "")
    public String CreateDate;

    @XmlElement(name = "DateTo", namespace = "")
    public String DateTo;

    @XmlElement(name = "ExchangeRateListTypeID", namespace = "")
    public int ExchangeRateListTypeID;

    @XmlElement(name = "CurrencyGroupID", namespace = "")
    public short CurrencyGroupID;

    @XmlElement(name = "CurrencyCode", namespace = "")
    public int CurrencyCode;

    @XmlElement(name = "CurrencyCodeNumChar", namespace = "")
    public String CurrencyCodeNumChar;
    @XmlElement(name = "CurrencyCodeAlfaChar", namespace = "")
    public String CurrencyCodeAlfaChar;
    @XmlElement(name = "CurrencyNameSerCyrl", namespace = "")
    public String CurrencyNameSerCyrl;
    @XmlElement(name = "CurrencyNameSerLat", namespace = "")
    public String CurrencyNameSerLat;
    @XmlElement(name = "CurrencyNameEng", namespace = "")
    public String CurrencyNameEng;
    @XmlElement(name = "CountryNameSerCyrl", namespace = "")
    public String CountryNameSerCyrl;
    @XmlElement(name = "CountryNameSerLat", namespace = "")
    public String CountryNameSerLat;
    @XmlElement(name = "CountryNameEng", namespace = "")
    public String CountryNameEng;
    @XmlElement(name = "Unit", namespace = "")
    public int Unit;
    @XmlElement(name = "BuyingRate", namespace = "")
    public double BuyingRate;
    @XmlElement(name = "MiddleRate", namespace = "")
    public double MiddleRate;
    @XmlElement(name = "SellingRate", namespace = "")
    public double SellingRate;
    @XmlElement(name = "FixingRate", namespace = "")
    public double FixingRate;

}
