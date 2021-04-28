package models;
import lombok.Data;

@Data
public class Address_Model {
    private String street;
    private String flat_no;
    private int pincode;
    private String type;
}
