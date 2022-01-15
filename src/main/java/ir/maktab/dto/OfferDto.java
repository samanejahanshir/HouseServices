package ir.maktab.dto;

import ir.maktab.data.model.Expert;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Builder
@Data
public class OfferDto {
    private Integer id;
    private Date offerCreateDate;
    private double offerPrice;
    private int durationTime;
    private int startTime;
    private Expert expert;

  /*  public static final class OfferDtoBuilder {
        private Integer id;
        private Date offerCreateDate;
        private double offerPrice;
        private int durationTime;
        private int startTime;

        private OfferDtoBuilder() {
        }

        public static OfferDtoBuilder anOfferDto() {
            return new OfferDtoBuilder();
        }

        public OfferDtoBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public OfferDtoBuilder withOfferCreateDate(Date offerCreateDate) {
            this.offerCreateDate = offerCreateDate;
            return this;
        }

        public OfferDtoBuilder withOfferPrice(double offerPrice) {
            this.offerPrice = offerPrice;
            return this;
        }

        public OfferDtoBuilder withDurationTime(int durationTime) {
            this.durationTime = durationTime;
            return this;
        }

        public OfferDtoBuilder withStartTime(int startTime) {
            this.startTime = startTime;
            return this;
        }

        public OfferDto build() {
            OfferDto offerDto = new OfferDto();
            offerDto.offerCreateDate = this.offerCreateDate;
            offerDto.durationTime = this.durationTime;
            offerDto.id = this.id;
            offerDto.startTime = this.startTime;
            offerDto.offerPrice = this.offerPrice;
            return offerDto;
        }
    }*/
}
