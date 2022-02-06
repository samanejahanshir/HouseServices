package ir.maktab.data.model;

import ir.maktab.data.enums.UserState;
import ir.maktab.data.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Customer extends User {
    @OneToMany(mappedBy = "customer")
    private List<Orders> orders = new ArrayList<>();

    @Builder
    public Customer(Integer id, String firstName, String lastName, String email, String password, UserState state, Date registerDate, double credit, List<Orders> orders, UserType role) {
        super(id, firstName, lastName, email, password, registerDate, role, state, credit);
        this.orders = orders;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
