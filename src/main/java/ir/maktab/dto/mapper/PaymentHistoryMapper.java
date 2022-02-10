package ir.maktab.dto.mapper;

import ir.maktab.data.model.PaymentHistory;
import ir.maktab.dto.PaymentHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentHistoryMapper {
    final CustomerMapper customerMapper;
    final OrderMapper orderMapper;

    public PaymentHistoryDto toDto(PaymentHistory paymentHistory) {
        return PaymentHistoryDto.builder()
                .id(paymentHistory.getId())
                .customer(customerMapper.toDto(paymentHistory.getCustomer()))
                .orders(orderMapper.toDto(paymentHistory.getOrders()))
                .paymentDate(paymentHistory.getPaymentDate())
                .type(paymentHistory.getType())
                .code(paymentHistory.getCode())
                .build();


    }

    public PaymentHistory toEntity(PaymentHistoryDto paymentHistoryDto) {
        return PaymentHistory.builder()
                .id(paymentHistoryDto.getId())
                .orders(orderMapper.toEntity(paymentHistoryDto.getOrders()))
                .customer(customerMapper.toEntity(paymentHistoryDto.getCustomer()))
                .paymentDate(paymentHistoryDto.getPaymentDate())
                .type(paymentHistoryDto.getType())
                .code(paymentHistoryDto.getCode())
                .build();
    }
}
