package ir.maktab.dto.mapper;

import ir.maktab.data.model.Customer;
import ir.maktab.data.model.User;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .registerDate(customer.getRegisterDate())
                .credit(customer.getCredit())
                .state(customer.getState())
                .build();
    }
    public Customer toEntity(CustomerDto customerDto){
        return  Customer.builder()
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .registerDate(customerDto.getRegisterDate())
                .credit(customerDto.getCredit())
                .build();
    }
}
