package ir.maktab.service;

import ir.maktab.data.dao.UserDao;
import ir.maktab.data.enums.UserState;
import ir.maktab.data.enums.UserType;
import ir.maktab.data.model.Customer;
import ir.maktab.data.model.Expert;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.UserDto;
import ir.maktab.dto.mapper.CustomerMapper;
import ir.maktab.dto.mapper.ExpertMapper;
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
    private final ExpertMapper expertMapper;
    private final CustomerMapper customerMapper;

    public void saveExpert(ExpertDto expertDto, String password) {
        Expert expert = expertMapper.toEntity(expertDto);
        expert.setPassword(password);
        expertService.saveExpert(expert);
    }

    public void saveCustomer(CustomerDto customerDto, String password) {
        Customer customer = customerMapper.toEntity(customerDto);
        customer.setPassword(password);
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
