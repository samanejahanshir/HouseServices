package ir.maktab.dto;

import ir.maktab.data.enums.OrderState;
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
public class OrdersSearch {
    private String subServiceName;
    private String MainServiceName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private OrderState State;

}
