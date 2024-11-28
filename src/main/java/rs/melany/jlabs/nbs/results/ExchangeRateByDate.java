/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.melany.jlabs.nbs.results;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents the elements of the exchange rate list for a specific date
 * as a result of calling the GetExchangeRateListByDate function from the National Bank service.
 * <p>
 * Each exchange rate entry contains information about the currency, rates, and related metadata.
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
public class ExchangeRateByDate {

    @Getter
    @XmlElement(name = "ExchangeRateListNumber", namespace = "")
    public int exchangeRateListNumber;

    @Getter
    @Setter
    @XmlElement(name = "Date", namespace = "")
    public String date;

    @XmlElement(name = "CreateDate", namespace = "")
    public String createDate;

    @XmlElement(name = "DateTo", namespace = "")
    public String dateTo;

    @Getter
    @Setter
    @XmlElement(name = "ExchangeRateListTypeID", namespace = "")
    public int exchangeRateListTypeID;

    @XmlElement(name = "CurrencyGroupID", namespace = "")
    public short currencyGroupID;

    @XmlElement(name = "CurrencyCode", namespace = "")
    public int currencyCode;

    @XmlElement(name = "CurrencyCodeNumChar", namespace = "")
    public String currencyCodeNumChar;

    @XmlElement(name = "CurrencyCodeAlfaChar", namespace = "")
    public String currencyCodeAlfaChar;

    @XmlElement(name = "CurrencyNameSerCyrl", namespace = "")
    public String currencyNameSerCyrl;

    @XmlElement(name = "CurrencyNameSerLat", namespace = "")
    public String currencyNameSerLat;

    @XmlElement(name = "CurrencyNameEng", namespace = "")
    public String currencyNameEng;

    @XmlElement(name = "CountryNameSerCyrl", namespace = "")
    public String countryNameSerCyrl;

    @XmlElement(name = "CountryNameSerLat", namespace = "")
    public String countryNameSerLat;

    @XmlElement(name = "CountryNameEng", namespace = "")
    public String countryNameEng;

    @Setter
    @XmlElement(name = "Unit", namespace = "")
    public int unit;

    @XmlElement(name = "BuyingRate", namespace = "")
    public double buyingRate;

    @XmlElement(name = "MiddleRate", namespace = "")
    public double middleRate;

    @Setter
    @XmlElement(name = "SellingRate", namespace = "")
    public double sellingRate;

    @XmlElement(name = "FixingRate", namespace = "")
    public double fixingRate;

    @Override
    public String toString() {
        return "ExchangeRateByDate{" +
                "exchangeRateListNumber=" + exchangeRateListNumber +
                ", date='" + date + '\'' +
                ", createDate='" + createDate + '\'' +
                ", dateTo='" + dateTo + '\'' +
                ", exchangeRateListTypeId=" + exchangeRateListTypeID +
                ", currencyGroupId=" + currencyGroupID +
                ", currencyCode=" + currencyCode +
                ", currencyCodeNumChar='" + currencyCodeNumChar + '\'' +
                ", currencyCodeAlfaChar='" + currencyCodeAlfaChar + '\'' +
                ", currencyNameSerCyrl='" + currencyNameSerCyrl + '\'' +
                ", currencyNameSerLat='" + currencyNameSerLat + '\'' +
                ", currencyNameEng='" + currencyNameEng + '\'' +
                ", countryNameSerCyrl='" + countryNameSerCyrl + '\'' +
                ", countryNameSerLat='" + countryNameSerLat + '\'' +
                ", countryNameEng='" + countryNameEng + '\'' +
                ", unit=" + unit +
                ", buyingRate=" + buyingRate +
                ", middleRate=" + middleRate +
                ", sellingRate=" + sellingRate +
                ", fixingRate=" + fixingRate +
                '}';
    }
}
