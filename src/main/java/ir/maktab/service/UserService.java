package ir.maktab.service;

import ir.maktab.data.dao.UserDao;
import ir.maktab.data.enums.UserState;
import ir.maktab.data.enums.UserType;
import ir.maktab.data.model.Customer;
import ir.maktab.data.model.Expert;
import ir.maktab.dto.CustomerDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Data
public class UserService {
    private final UserDao userDao;
    private final ExpertService expertService;
    private final CustomerService customerService;


    public void saveExpert(Expert expert) {
        expert.setRole(UserType.EXPERT);
        expertService.saveExpert(expert);
    }

    public void saveCustomer(Customer customer) {
        customer.setRole(UserType.CUSTOMER);
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
