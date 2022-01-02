package model;

import lombok.Builder;
import lombok.Data;
import model.enums.OrderState;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private double proposedPrice;
    private  String description;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date orderRegisterDate;
    @Temporal(TemporalType.DATE)
    private Date orderDoneDate;
    private int orderDoneTime;
    @OneToOne
    private Address address;
    @Enumerated(EnumType.STRING)
    private OrderState state;
    @OneToOne
    private Services services;
    @ManyToOne
    private Customer customer;

    public Order() {

    }
}
