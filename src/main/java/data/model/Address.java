package data.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String city;
    private String street;
    private String postalCode;
    private String tag;
   /* @ManyToOne
    private User user;*/

    public Address() {

    }

    public static final class AddressBuilder {
        private String city;
        private String street;
        private String postalCode;
        private String tag;
        //  private User user;

        private AddressBuilder() {
        }

        public static AddressBuilder anAddress() {
            return new AddressBuilder();
        }

        public AddressBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder withStreet(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder withPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public AddressBuilder withTag(String tag) {
            this.tag = tag;
            return this;
        }

      /*  public AddressBuilder withUser(User user) {
            this.user = user;
            return this;
        }*/

        public Address build() {
            Address address = new Address();
            address.setCity(city);
            address.setStreet(street);
            address.setPostalCode(postalCode);
            address.setTag(tag);
            //    address.setUser(user);
            return address;
        }
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
