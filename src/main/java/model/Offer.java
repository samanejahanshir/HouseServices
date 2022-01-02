package model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date offerCreateDate;
    private double offerPrice;
    private int doneTime;
    private int startTime;
    @ManyToOne
    private Orders orders;

    public static final class OfferBuilder {
        private Date offerCreateDate;
        private double offerPrice;
        private int doneTime;
        private int startTime;

        private OfferBuilder() {
        }

        public static OfferBuilder anOffer() {
            return new OfferBuilder();
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
            this.doneTime = doneTime;
            return this;
        }

        public OfferBuilder withStartTime(int startTime) {
            this.startTime = startTime;
            return this;
        }

        public Offer build() {
            Offer offer = new Offer();
            offer.setOfferCreateDate(offerCreateDate);
            offer.setOfferPrice(offerPrice);
            offer.setDoneTime(doneTime);
            offer.setStartTime(startTime);
            return offer;
        }
    }
}
