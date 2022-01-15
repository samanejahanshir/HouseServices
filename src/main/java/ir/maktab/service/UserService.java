package ir.maktab.service;

import ir.maktab.data.dao.UserDao;
import ir.maktab.data.enums.UserState;
import ir.maktab.data.model.Customer;
import ir.maktab.data.model.Expert;
import ir.maktab.data.model.User;
import ir.maktab.dto.CustomerDto;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Data
public class UserService {
    private final UserDao userDao;
    private  final ExpertService expertService;
    private  final CustomerService customerService;


    public void saveExpert(Expert expert) {
        expertService.saveExpert(expert);
    }

    public void saveCustomer(Customer customer) {
        customerService.saveCustomer(customer);
    }

/*
    public User getUserByEmail(String email, String password) {
        return userDao.getUserByEmail(email, password);
    }
*/
public boolean checkConfirmUser(CustomerDto customerDto) {
    Customer customer = customerService.getCustomerByEmail(customerDto.getEmail());
    if (customer.getState().equals(UserState.CONFIRMED)) {
        return true;
    } else {
        return false;
    }
}
}
