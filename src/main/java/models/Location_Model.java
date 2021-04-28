package models;

import java.util.List;

import lombok.Data;

@Data
public class Location_Model {
    private int id;
    private String city;
    private String country;
    private List<Address_Model> address;
}
