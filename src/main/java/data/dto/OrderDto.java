package data.dto;

import data.enums.OrderState;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {
    private Integer id;
    private double proposedPrice;
    private String description;
    private Date orderRegisterDate;
    private Date orderDoingDate;
    private int orderDoingTime;
    private OrderState state;

    public static final class OrderDtoBuilder {
        private Integer id;
        private double proposedPrice;
        private String description;
        private Date orderRegisterDate;
        private Date orderDoingDate;
        private int orderDoingTime;
        private OrderState state;

        private OrderDtoBuilder() {
        }

        public static OrderDtoBuilder anOrderDto() {
            return new OrderDtoBuilder();
        }

        public OrderDtoBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public OrderDtoBuilder withProposedPrice(double proposedPrice) {
            this.proposedPrice = proposedPrice;
            return this;
        }

        public OrderDtoBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public OrderDtoBuilder withOrderRegisterDate(Date orderRegisterDate) {
            this.orderRegisterDate = orderRegisterDate;
            return this;
        }

        public OrderDtoBuilder withOrderDoingDate(Date orderDoingDate) {
            this.orderDoingDate = orderDoingDate;
            return this;
        }

        public OrderDtoBuilder withOrderDoingTime(int orderDoingTime) {
            this.orderDoingTime = orderDoingTime;
            return this;
        }

        public OrderDtoBuilder withState(OrderState state) {
            this.state = state;
            return this;
        }

        public OrderDto build() {
            OrderDto orderDto = new OrderDto();
            orderDto.orderRegisterDate = this.orderRegisterDate;
            orderDto.orderDoingDate = this.orderDoingDate;
            orderDto.proposedPrice = this.proposedPrice;
            orderDto.id = this.id;
            orderDto.orderDoingTime = this.orderDoingTime;
            orderDto.state = this.state;
            orderDto.description = this.description;
            return orderDto;
        }
    }
}
