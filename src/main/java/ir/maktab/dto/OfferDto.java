package ir.maktab.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import ir.maktab.data.enums.OfferState;
import ir.maktab.data.model.Expert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "OfferDto")
public class OfferDto {
    private Integer id;
    @ApiModelProperty(dataType = "Date",value = "date of register offer ")
    private Date offerCreateDate;
    @ApiModelProperty(dataType = "double",value = "price that expert offered ")
    private double offerPrice;
    @ApiModelProperty(dataType = "int",value = "The time it takes to place an order ")
    private int durationTime;
    @ApiModelProperty(dataType = "int",value = "time of start order ")
    private int startTime;
    @ApiModelProperty(dataType = "ExpertDto",value = "expert that register this offer")
    private ExpertDto expertDto;
    @ApiModelProperty(dataType = "OrderDto",value = "Offer belongs to that order")
    private OrderDto orderDto;
    @ApiModelProperty(dataType = "OfferState",value = "state of offer that is new or accept or reject")
    private OfferState state;

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
