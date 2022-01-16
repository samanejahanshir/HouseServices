package ir.maktab.dto.mapper;

import ir.maktab.data.model.Offer;
import ir.maktab.data.model.Orders;
import ir.maktab.dto.OfferDto;
import ir.maktab.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class OrderMapper {
    private  final SubServiceMapper subServiceMapper;
    private  final ExpertMapper expertMapper;
    private final CustomerMapper customerMapper;
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
                .subServiceDto(subServiceMapper.toDto(orders.getSubServices()))
              //  .expertDto(expertMapper.toDto(orders.getExpert()))
                .customerDto(customerMapper.toDto(orders.getCustomer()))
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
                .subServices(subServiceMapper.toEntity(orderDto.getSubServiceDto()))
                .customer(customerMapper.toEntity(orderDto.getCustomerDto()))
               // .expert(expertMapper.toEntity(orderDto.getExpertDto()))
                .build();
    }
}
