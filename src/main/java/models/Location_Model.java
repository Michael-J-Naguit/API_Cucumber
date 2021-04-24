package models;

import java.util.List;

public class Location_Model {
    private int id;
    private String city;
    private String country;
    private List<Address_Model> address;

    public void Location_Model() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAddress(List<Address_Model> address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public List<Address_Model> getAddress() {
        return address;
    }
}
