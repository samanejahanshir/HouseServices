package ir.maktab.dto;

import ir.maktab.data.enums.OrderState;
import ir.maktab.data.model.Address;
import ir.maktab.data.model.SubServices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Integer id;
    private double proposedPrice;
    private String description;
    private Date orderRegisterDate;
    private Date orderDoingDate;
    private int orderDoingTime;
    private OrderState state;
    private Address address;
    private SubServiceDto subServiceDto;
    private ExpertDto expertDto;
    private CustomerDto customerDto;

}
