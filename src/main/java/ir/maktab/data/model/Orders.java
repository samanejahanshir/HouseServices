package ir.maktab.data.model;

import ir.maktab.data.enums.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double proposedPrice;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date orderRegisterDate;
    @Temporal(TemporalType.DATE)
    private Date orderDoingDate;
    private int orderDoingTime;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Address address;
    @Enumerated(EnumType.STRING)
    private OrderState state;
    @OneToOne
    private SubServices subServices;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Expert expert;
    /*@OneToMany(mappedBy = "orders",cascade = CascadeType.REMOVE)
    private List<Offer> offers = new ArrayList<>();*/
    @OneToOne
    private Commend commend;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", proposedPrice=" + proposedPrice +
                ", description='" + description + '\'' +
                ", orderRegisterDate=" + orderRegisterDate +
                ", orderDoneDate=" + orderDoingDate +
                ", orderDoneTime=" + orderDoingTime +
                ", address=" + address +
                ", state=" + state +
                ", subServices=" + subServices +
                ", customer=" + customer +
                ", expert=" + expert +
                '}';
    }
}

