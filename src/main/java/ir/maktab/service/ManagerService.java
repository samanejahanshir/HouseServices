package ir.maktab.service;

import ir.maktab.data.dao.*;
import ir.maktab.data.enums.UserState;
import ir.maktab.data.model.*;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.MainServiceDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.dto.UserDto;
import ir.maktab.dto.mapper.CustomerMapper;
import ir.maktab.dto.mapper.MainServiceMapper;
import ir.maktab.dto.mapper.SubServiceMapper;
import ir.maktab.dto.mapper.UserMapper;
import ir.maktab.exceptions.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Data
public class ManagerService {
    private final ManagerDao managerDao;
    private final SubServiceDao servicesDao;
    private final MainServiceDao mainServiceDao;
    private final ExpertDao expertDao;
    private final UserDao userDao;
   // private final CustomerDao customerDao;
    private final SubServiceMapper subServiceMapper;
    private final MainServiceMapper mainServiceMapper;
    private final UserMapper userMapper;
    private final CustomerMapper customerMapper;
    private final CustomerService customerService;


    public void saveSubService(SubServiceDto subServiceDto) {
        Optional<MainServices> mainServiceOptional = mainServiceDao.findByGroupName(subServiceDto.getGroupName());
        if (mainServiceOptional.isPresent()) {
            MainServices mainServices = mainServiceOptional.get();
            if (servicesDao.findByName(subServiceDto.getName()).isEmpty()) {
                SubServices subServices = subServiceMapper.toEntity(subServiceDto);
                subServices.setMainServices(mainServices);
                servicesDao.save(subServices);
            } else {
                throw new SubServiceDuplicateException();
            }
        } else {
            throw new MainServiceNotFoundException();
        }
    }

    @Transactional
    public void deleteMainServices(String groupName) {
        List<SubServices> subServicesList = servicesDao.findAllByMainServices_GroupName(groupName);
        List<Expert> experts = expertDao.getListExpertByGroupName(groupName);
        for (Expert expert : experts) {
            expert.getServices().removeAll(expert.getServices().stream().filter(subServices -> subServices.getMainServices().getGroupName().equals(groupName)).collect(Collectors.toList()));
        }
        expertDao.saveAll(experts);
        servicesDao.deleteAll(subServicesList);
        mainServiceDao.deleteByGroupName(groupName);
    }

    @Transactional
    public void deleteSubServices(String subServices) {
        //TODO delete from expert list
        Optional<SubServices> subServiceOptional = servicesDao.findByName(subServices);
        if (subServiceOptional.isPresent()) {
            SubServices subService = subServiceOptional.get();
            List<Expert> experts = expertDao.getListExpertBySubServiceName(subServices);
            for (Expert expert : experts) {
                expert.getServices().remove(subService);
            }
            expertDao.saveAll(experts);
            servicesDao.delete(subService);
        } else {
            throw new SubServiceNotFoundException();
        }
    }

    public void saveMainServiceToDb(MainServiceDto mainServiceDto) {
        Optional<MainServices> mainServiceOptional = mainServiceDao.findByGroupName(mainServiceDto.getGroupName());
        if (mainServiceOptional.isEmpty()) {
            MainServices mainServices = mainServiceMapper.toEntity(mainServiceDto);
            mainServiceDao.save(mainServices);
        } else {
            throw new MainServiceDuplicateException();
        }
    }

    public List<UserDto> getListUsers() {
        List<User> users = userDao.findAll();
        return users.stream().map(userMapper::toDto).collect(Collectors.toList());
    }

   /* public List<User> getListUsersByCondition(UserType type, String email, String name, String family) {
        return userDao.getListUserByCondition(type, email, name, family);

    }*/

    public List<CustomerDto> getListCustomerNoConfirm() {
        List<Customer> customers = customerService.getCustomerDao().findByStateEquals(UserState.NOT_CONFIRMED);
        return customers.stream().map(customerMapper::toDto).collect(Collectors.toList());
    }

    public void confirmCustomer(CustomerDto customerDto) {
        Customer customer = customerService.getCustomerByEmail(customerDto.getEmail());
        if (customer!=null) {
            customer.setState(UserState.CONFIRMED);
            customerService.getCustomerDao().save(customer);
        } else {
            throw new CustomerNotExistException();
        }

    }

    public Manager getManagerByNameAndPass(String userName, String password) {
        Optional<Manager> managerOptional = managerDao.findByUserNameAndPassword(userName, password);
        if (managerOptional.isPresent()) {
            return managerOptional.get();
        } else {
            throw new ManagerNotFoundException();
        }
    }

    public void saveManager(Manager manager) {
        managerDao.save(manager);
    }
}
