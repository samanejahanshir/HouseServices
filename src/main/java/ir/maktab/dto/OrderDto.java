package ir.maktab.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import ir.maktab.data.enums.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "OrderDto")
public class OrderDto {
    private Integer id;
    @ApiModelProperty(dataType = "double", value = "price of order ")
    private double proposedPrice;
    @ApiModelProperty(dataType = "String", value = "description of order")
    private String description;
    @ApiModelProperty(dataType = "Date", value = "date of order register")
    private Date orderRegisterDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(dataType = "Date", value = "date of order doing for customer")
    private Date orderDoingDate;
    @Min(value = 1, message = "min time should be 1")
    @Max(value = 24, message = "max time should be 24")
    @ApiModelProperty(dataType = "int", value = "time for doing order for customer")
    private int orderDoingTime;
    @ApiModelProperty(dataType = "OrderState",value = "state of order that is at which stage is it")
    private OrderState state;
    @ApiModelProperty(dataType = "String", value = "address of order")
    private AddressDto address;
    @ApiModelProperty(dataType = "String", value = "uuid is uniq id of order")
    private String uuid;
    @ApiModelProperty(dataType = "SubServiceDto", value = "subService of order")
    private SubServiceDto subServiceDto;
    @ApiModelProperty(dataType = "ExpertDto", value = "expert that doing this order")
    private ExpertDto expertDto;
    @ApiModelProperty(dataType = "CustomerDto", value = "customer that register this order")
    private CustomerDto customerDto;
    @ApiModelProperty(dataType = "CommendDto", value = "command that add by customer for order")
    private CommendDto commendDto;


}
