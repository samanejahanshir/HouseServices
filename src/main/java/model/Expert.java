package model;

import lombok.Data;
import model.enums.UserState;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
public class Expert extends User {
    @Lob
    private byte[] image;
    @ManyToMany
    private List<SubServices> services = new ArrayList<>();
    @OneToMany(mappedBy = "expert")
    private List<Orders> orders = new ArrayList<>();
    @OneToOne
    private Offer offer;

    public static final class ExpertBuilder {
        private byte[] image;
        private List<SubServices> services = new ArrayList<>();
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private UserState state;
        private Date registerDate;
        private long credit;
        private List<Address> addresses = new ArrayList<>();
        @OneToMany(mappedBy = "expert")
        private List<Orders> orders = new ArrayList<>();

        ExpertBuilder() {
        }

        public static ExpertBuilder anExpert() {
            return new ExpertBuilder();
        }

        public ExpertBuilder withImage(byte[] image) {
            this.image = image;
            return this;
        }

        public ExpertBuilder withServices(List<SubServices> services) {
            this.services = services;
            return this;
        }

        public ExpertBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ExpertBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ExpertBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ExpertBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public ExpertBuilder withState(UserState state) {
            this.state = state;
            return this;
        }

        public ExpertBuilder withRegisterDate(Date registerDate) {
            this.registerDate = registerDate;
            return this;
        }

        public ExpertBuilder withCredit(long credit) {
            this.credit = credit;
            return this;
        }

        public ExpertBuilder withAddresses(List<Address> addresses) {
            this.addresses = addresses;
            return this;
        }

        public Expert build() {
            Expert expert = new Expert();
            expert.setImage(image);
            expert.setServices(services);
            expert.setFirstName(firstName);
            expert.setLastName(lastName);
            expert.setEmail(email);
            expert.setPassword(password);
            expert.setState(state);
            expert.setRegisterDate(registerDate);
            expert.setCredit(credit);
            expert.setAddresses(addresses);
            return expert;
        }
    }

    @Override
    public String toString() {
        return "Expert{" +
                "image=" + Arrays.toString(image) +
                ", services=" + services +
                '}';
    }
}
