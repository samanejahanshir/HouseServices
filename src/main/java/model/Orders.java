package model;

import lombok.Data;
import model.enums.OrderState;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double proposedPrice;
    private String description;
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
    private SubServices subServices;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Expert expert;
    @OneToMany(mappedBy = "orders")
    private List<Offer> offers=new ArrayList<>();
    private String Comment;

    public Orders() {

    }

    public static final class OrderBuilder {
        private double proposedPrice;
        private String description;
        private Date orderRegisterDate;
        private Date orderDoneDate;
        private int orderDoneTime;
        private Address address;
        private OrderState state;
        private SubServices subServices;
        private Customer customer;

        private OrderBuilder() {
        }

        public static OrderBuilder anOrder() {
            return new OrderBuilder();
        }

        public OrderBuilder withProposedPrice(double proposedPrice) {
            this.proposedPrice = proposedPrice;
            return this;
        }

        public OrderBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public OrderBuilder withOrderRegisterDate(Date orderRegisterDate) {
            this.orderRegisterDate = orderRegisterDate;
            return this;
        }

        public OrderBuilder withOrderDoneDate(Date orderDoneDate) {
            this.orderDoneDate = orderDoneDate;
            return this;
        }

        public OrderBuilder withOrderDoneTime(int orderDoneTime) {
            this.orderDoneTime = orderDoneTime;
            return this;
        }

        public OrderBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public OrderBuilder withState(OrderState state) {
            this.state = state;
            return this;
        }

        public OrderBuilder withServices(SubServices subServices) {
            this.subServices = subServices;
            return this;
        }

        public OrderBuilder withCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Orders build() {
            Orders order = new Orders();
            order.setProposedPrice(proposedPrice);
            order.setDescription(description);
            order.setOrderRegisterDate(orderRegisterDate);
            order.setOrderDoneDate(orderDoneDate);
            order.setOrderDoneTime(orderDoneTime);
            order.setAddress(address);
            order.setState(state);
            order.setSubServices(subServices);
            order.setCustomer(customer);
            return order;
        }
    }
}
