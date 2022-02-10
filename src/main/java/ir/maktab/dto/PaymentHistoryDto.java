package ir.maktab.dto;

import ir.maktab.data.model.Customer;
import ir.maktab.data.model.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentHistoryDto {
    private  int id;
    private OrderDto orders;
    private CustomerDto customer;
    private Date paymentDate;
    private String type;
    private  String code;

}
