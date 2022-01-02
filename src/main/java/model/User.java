package model;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import model.enums.UserState;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserState state;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerDate;
    private long credit;
    @OneToMany
    private List<Address> addresses=new ArrayList<>();

    public User() {

    }


}
