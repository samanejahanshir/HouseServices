package ir.maktab.service;

import ir.maktab.data.dao.UserDao;
import ir.maktab.data.enums.UserState;
import ir.maktab.data.enums.UserType;
import ir.maktab.data.model.Customer;
import ir.maktab.data.model.Expert;
import ir.maktab.data.model.User;
import ir.maktab.dto.ConditionSearch;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.UserDto;
import ir.maktab.dto.mapper.CustomerMapper;
import ir.maktab.dto.mapper.ExpertMapper;
import ir.maktab.dto.mapper.UserMapper;
import ir.maktab.exceptions.UserNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Data
public class UserService {
    private final UserDao userDao;
    private final ExpertService expertService;
    private final CustomerService customerService;
    private final ExpertMapper expertMapper;
    private final CustomerMapper customerMapper;
    private final UserMapper userMapper;

    public void saveExpert(ExpertDto expertDto) {
        Expert expert = expertMapper.toEntity(expertDto);
        expert.setRole(UserType.EXPERT);
        expertService.saveExpert(expert);
    }

    public void saveCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        customer.setRole(UserType.CUSTOMER);
        customerService.saveCustomer(customer);
    }

    public boolean checkConfirmUser(CustomerDto customerDto) {
        Customer customer = customerService.getCustomerByEmail(customerDto.getEmail());
        if (customer.getState().equals(UserState.CONFIRMED)) {
            return true;
        } else {
            return false;
        }
    }

    public User getUserById(int id) {
        Optional<User> optionalUser = userDao.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException();
        }
    }

    public List<UserDto> getUserByCondition(ConditionSearch condition) {
        if (condition != null) {
            if (condition.getOrderUser() != null && condition.getOrderUser().equals("expert")) {
                return getCustomerByCondition(condition);
            } else if (condition.getOrderUser() != null && condition.getOrderUser().equals("customer")) {
                return getCustomerByCondition(condition);
            } else {
                List<User> userList = userDao.findAll(UserDao.selectByCondition(condition), Sort.by("registerDate"));
                if (!(userList.isEmpty())) {
                    return userList.stream().map(userMapper::toDto).collect(Collectors.toList());
                } else {
                    throw new UserNotFoundException();
                }
            }
        } else {
            return userDao.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
        }
    }

    public List<UserDto> getCustomerByCondition(ConditionSearch condition) {
        List<CustomerDto> customerDtos = customerService.getCustomerByCondition(condition);
        return customerDtos.stream().map(userMapper::customerDtoToUserDto).collect(Collectors.toList());
    }

    public List<UserDto> getExpertsByCondition(ConditionSearch condition) {
        if(condition!=null) {
            List<ExpertDto> expertDtoList = expertService.getExpertsByCondition(condition);
            return expertDtoList.stream().map(userMapper::expertDtoToUserDto).collect(Collectors.toList());
        }else {
            List<Expert> expertList = expertService.getExpertDao().findAll();
          return   expertList.stream().map(expertMapper::toDto).map(userMapper::expertDtoToUserDto).collect(Collectors.toList());
        }
    }
}
