package ir.maktab.data.model;

import ir.maktab.data.enums.UserState;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Customer extends User {
    @OneToMany(mappedBy = "customer")
    private List<Orders> orders = new ArrayList<>();

    public static final class CustomerBuilder {
        private List<Orders> orders = new ArrayList<>();
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private UserState state;
        private Date registerDate;
        private long credit;
        private List<Address> addresses = new ArrayList<>();

        CustomerBuilder() {
        }

        public static CustomerBuilder aCustomer() {
            return new CustomerBuilder();
        }

        public CustomerBuilder withOrders(List<Orders> orders) {
            this.orders = orders;
            return this;
        }

        public CustomerBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public CustomerBuilder withState(UserState state) {
            this.state = state;
            return this;
        }

        public CustomerBuilder withRegisterDate(Date registerDate) {
            this.registerDate = registerDate;
            return this;
        }

        public CustomerBuilder withCredit(long credit) {
            this.credit = credit;
            return this;
        }

        public CustomerBuilder withAddresses(List<Address> addresses) {
            this.addresses = addresses;
            return this;
        }

        public Customer build() {
            Customer customer = new Customer();
            customer.setOrders(orders);
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customer.setPassword(password);
            customer.setState(state);
            customer.setRegisterDate(registerDate);
            customer.setCredit(credit);
            customer.setAddresses(addresses);
            return customer;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
