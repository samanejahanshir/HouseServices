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


    public static final class OrdersBuilder {
        private double proposedPrice;
        private String description;
        private Date orderRegisterDate;
        private Date orderDoneDate;
        private int orderDoneTime;
        private Address address;
        private OrderState state;
        private SubServices subServices;
        private Customer customer;
        private Expert expert;
        private List<Offer> offers=new ArrayList<>();
        private String Comment;

        private OrdersBuilder() {
        }

        public static OrdersBuilder anOrders() {
            return new OrdersBuilder();
        }

        public OrdersBuilder withProposedPrice(double proposedPrice) {
            this.proposedPrice = proposedPrice;
            return this;
        }

        public OrdersBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public OrdersBuilder withOrderRegisterDate(Date orderRegisterDate) {
            this.orderRegisterDate = orderRegisterDate;
            return this;
        }

        public OrdersBuilder withOrderDoneDate(Date orderDoneDate) {
            this.orderDoneDate = orderDoneDate;
            return this;
        }

        public OrdersBuilder withOrderDoneTime(int orderDoneTime) {
            this.orderDoneTime = orderDoneTime;
            return this;
        }

        public OrdersBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public OrdersBuilder withState(OrderState state) {
            this.state = state;
            return this;
        }

        public OrdersBuilder withSubServices(SubServices subServices) {
            this.subServices = subServices;
            return this;
        }

        public OrdersBuilder withCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public OrdersBuilder withExpert(Expert expert) {
            this.expert = expert;
            return this;
        }

        public OrdersBuilder withOffers(List<Offer> offers) {
            this.offers = offers;
            return this;
        }

        public OrdersBuilder withComment(String Comment) {
            this.Comment = Comment;
            return this;
        }

        public Orders build() {
            Orders orders = new Orders();
            orders.setProposedPrice(proposedPrice);
            orders.setDescription(description);
            orders.setOrderRegisterDate(orderRegisterDate);
            orders.setOrderDoneDate(orderDoneDate);
            orders.setOrderDoneTime(orderDoneTime);
            orders.setAddress(address);
            orders.setState(state);
            orders.setSubServices(subServices);
            orders.setCustomer(customer);
            orders.setExpert(expert);
            orders.setOffers(offers);
            orders.setComment(Comment);
            return orders;
        }
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", proposedPrice=" + proposedPrice +
                ", description='" + description + '\'' +
                ", orderRegisterDate=" + orderRegisterDate +
                ", orderDoneDate=" + orderDoneDate +
                ", orderDoneTime=" + orderDoneTime +
                ", address=" + address +
                ", state=" + state +
                ", subServices=" + subServices +
                ", customer=" + customer +
                ", expert=" + expert +
                ", Comment='" + Comment + '\'' +
                '}';
    }
}

