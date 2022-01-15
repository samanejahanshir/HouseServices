package ir.maktab.dto.mapper;

import ir.maktab.data.model.Offer;
import ir.maktab.data.model.Orders;
import ir.maktab.dto.OfferDto;
import ir.maktab.dto.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDto toDto(Orders orders) {
        return OrderDto.builder()
                .id(orders.getId())
                .description(orders.getDescription())
                .orderDoingDate(orders.getOrderDoingDate())
                .orderDoingTime(orders.getOrderDoingTime())
                .orderRegisterDate(orders.getOrderRegisterDate())
                .proposedPrice(orders.getProposedPrice())
                .address(orders.getAddress())
                .state(orders.getState())
                .subService(orders.getSubServices())
                .build();
    }
    public Orders toEntity(OrderDto orderDto){
        return  Orders.builder()
                .id(orderDto.getId())
                .orderDoingDate(orderDto.getOrderDoingDate())
                .orderDoingTime(orderDto.getOrderDoingTime())
                .description(orderDto.getDescription())
                .orderRegisterDate(orderDto.getOrderRegisterDate())
                .proposedPrice(orderDto.getProposedPrice())
                .address(orderDto.getAddress())
                .subServices(orderDto.getSubService())
                .build();
    }
}
