package ir.maktab.dto;

import ir.maktab.data.enums.OrderState;
import ir.maktab.data.model.Address;
import ir.maktab.data.model.SubServices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @Min(value = 1,message = "min time should be 1")
    @Max(value = 24,message = "max time should be 24")
    private int orderDoingTime;
    private OrderState state;
    private AddressDto address;
    private String uuid;
    private SubServiceDto subServiceDto;
    private ExpertDto expertDto;
    private CustomerDto customerDto;
    private CommendDto commendDto;


}
