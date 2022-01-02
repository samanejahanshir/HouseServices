package model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String city;
    private  String street;
    private String postalCode;
    private String tag;
    @ManyToOne
    private User user;

    public Address() {

    }
}
