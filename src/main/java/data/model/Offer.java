package data.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date offerCreateDate;
    private double offerPrice;
    private int durationTime;

    private int startTime;
    @ManyToOne(fetch = FetchType.EAGER)
    private Orders orders;
    @ManyToOne
    private Expert expert;

    public static final class OfferBuilder {
        private int id;
        private Date offerCreateDate;
        private double offerPrice;
        private int durationTime;
        private int startTime;
        private Orders orders;
        private Expert expert;

        private OfferBuilder() {
        }

        public static OfferBuilder anOffer() {
            return new OfferBuilder();
        }

        public OfferBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public OfferBuilder withOfferCreateDate(Date offerCreateDate) {
            this.offerCreateDate = offerCreateDate;
            return this;
        }

        public OfferBuilder withOfferPrice(double offerPrice) {
            this.offerPrice = offerPrice;
            return this;
        }

        public OfferBuilder withDoneTime(int doneTime) {
            this.durationTime = doneTime;
            return this;
        }

        public OfferBuilder withStartTime(int startTime) {
            this.startTime = startTime;
            return this;
        }

        public OfferBuilder withOrders(Orders orders) {
            this.orders = orders;
            return this;
        }

        public OfferBuilder withExpert(Expert expert) {
            this.expert = expert;
            return this;
        }

        public Offer build() {
            Offer offer = new Offer();
            offer.setId(id);
            offer.setOfferCreateDate(offerCreateDate);
            offer.setOfferPrice(offerPrice);
            offer.setDurationTime(durationTime);
            offer.setStartTime(startTime);
            offer.setOrders(orders);
            offer.setExpert(expert);
            return offer;
        }
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", offerCreateDate=" + offerCreateDate +
                ", offerPrice=" + offerPrice +
                ", doneTime=" + durationTime +
                ", startTime=" + startTime +
                ", expert=" + expert +
                '}';
    }
}
