package ir.maktab.service;

import ir.maktab.data.dao.PaymentHistoryDao;
import ir.maktab.data.model.Customer;
import ir.maktab.data.model.Expert;
import ir.maktab.data.model.PaymentHistory;
import ir.maktab.dto.OrderDto;
import ir.maktab.dto.PaymentHistoryDto;
import ir.maktab.dto.mapper.PaymentHistoryMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Data
public class PaymentHistoryService {
    final OrderService orderService;
    final CustomerService customerService;
    final PaymentHistoryMapper paymentHistoryMapper;
    final PaymentHistoryDao paymentHistoryDao;

    public void save(int orderId, String email, String type) {
        Customer customerByEmail = customerService.getCustomerByEmail(email);
        OrderDto orderById = orderService.getOrderById(orderId);
        PaymentHistory paymentHistory = new PaymentHistory();
        paymentHistory.setOrders(orderService.getOrderMapper().toEntity(orderById));
        paymentHistory.setCustomer(customerByEmail);
        Double random = Math.random() * ((99999999 - 1000000 + 1) + 10000000);
        paymentHistory.setCode(String.valueOf(random.intValue()));
        paymentHistory.setType(type);
        // PaymentHistory paymentHistory = paymentHistoryMapper.toEntity(paymentHistoryDto);
        paymentHistoryDao.save(paymentHistory);
    }

    @Transactional
    public List<PaymentHistoryDto> getListPaymentsHistory(String email) {
        List<PaymentHistory> paymentHistories = paymentHistoryDao.findByCustomer_Email(email);
        return paymentHistories.stream().map(paymentHistory -> {
            Expert expert = paymentHistory.getOrders().getExpert();
            OrderDto orderDto = orderService.getOrderMapper().toDto(paymentHistory.getOrders());
            orderDto.setExpertDto(orderService.getExpertService().getExpertMapper().toDto(expert));
            PaymentHistoryDto paymentHistoryDto = paymentHistoryMapper.toDto(paymentHistory);
            paymentHistoryDto.setOrders(orderDto);
            return paymentHistoryDto;
        }).collect(Collectors.toList());
    }
}
