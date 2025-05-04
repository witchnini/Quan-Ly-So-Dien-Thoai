package qlsdt_swing.qlsdt.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "phoneNumber")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneNumber implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String fullName;
    private String idNumber;
    private String phoneNumber;
    private String carrier;
    private String servicePackage;
    private double price;
    private String connectionTime;

    private double fromPriceSearch;
    private double toPriceSearch;
    private String phoneNumberSearch;

    public PhoneNumber() {
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(String connectionTime) {
        this.connectionTime = connectionTime;
    }

    public double getFromPriceSearch() {
        return fromPriceSearch;
    }

    public void setFromPriceSearch(double fromPriceSearch) {
        this.fromPriceSearch = fromPriceSearch;
    }

    public double getToPriceSearch() {
        return toPriceSearch;
    }

    public void setToPriceSearch(double toPriceSearch) {
        this.toPriceSearch = toPriceSearch;
    }

    public String getPhoneNumberSearch() {
        return phoneNumberSearch;
    }

    public void setPhoneNumberSearch(String phoneNumberSearch) {
        this.phoneNumberSearch = phoneNumberSearch;
    }
}

