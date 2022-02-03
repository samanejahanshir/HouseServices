package ir.maktab.dto;

import com.wordnik.swagger.annotations.ApiModel;
import ir.maktab.data.enums.OrderState;
import ir.maktab.data.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "OrdersSearch")
public class OrdersSearch {
    private String subServiceName;
    private String MainServiceName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private OrderState State;
    private UserType userType;

}
