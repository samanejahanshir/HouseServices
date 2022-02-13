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
    //private final SubServiceDao servicesDao;
    //private final MainServiceDao mainServiceDao;
   // private final ExpertDao expertDao;
   // private final UserDao userDao;
    //private final SubServiceMapper subServiceMapper;
    //private final MainServiceMapper mainServiceMapper;
    //private final UserMapper userMapper;
    //private final CustomerMapper customerMapper;
    private  final  ExpertService expertService;
    private final CustomerService customerService;
    final MainServicesService mainServicesService;
    final  SubServicesService subServicesService;
    private final UserService userService;



    public void saveSubService(SubServiceDto subServiceDto) {
        Optional<MainServices> mainServiceOptional =mainServicesService.getMainServiceDao().findByGroupName(subServiceDto.getGroupName());
        if (mainServiceOptional.isPresent()) {
            MainServices mainServices = mainServiceOptional.get();
            if (subServicesService.getSubServices().findByName(subServiceDto.getName()).isEmpty()) {
                SubServices subServices = subServicesService.getSubServiceMapper().toEntity(subServiceDto);
                subServices.setMainServices(mainServices);
               subServicesService.getSubServices().save(subServices);
            } else {
                throw new SubServiceDuplicateException();
            }
        } else {
            throw new MainServiceNotFoundException();
        }
    }

    @Transactional
    public void deleteMainServices(String groupName) {
        List<SubServices> subServicesList = subServicesService.getSubServices().findAllByMainServices_GroupName(groupName);
        List<Expert> experts = expertService.getExpertDao().getListExpertByGroupName(groupName);
        for (Expert expert : experts) {
            expert.getServices().removeAll(expert.getServices().stream().filter(subServices -> subServices.getMainServices().getGroupName().equals(groupName)).collect(Collectors.toList()));
        }
        expertService.getExpertDao().saveAll(experts);
        subServicesService.getSubServices().deleteAll(subServicesList);
        mainServicesService.getMainServiceDao().deleteByGroupName(groupName);
    }

    @Transactional
    public void deleteSubServices(String subServices) {
        //TODO delete from expert list
        Optional<SubServices> subServiceOptional = subServicesService.getSubServices().findByName(subServices);
        if (subServiceOptional.isPresent()) {
            SubServices subService = subServiceOptional.get();
            List<Expert> experts = expertService.getExpertDao().getListExpertBySubServiceName(subServices);
            for (Expert expert : experts) {
                expert.getServices().remove(subService);
            }
            expertService.getExpertDao().saveAll(experts);
            subServicesService.getSubServices().delete(subService);
        } else {
            throw new SubServiceNotFoundException();
        }
    }

    public void saveMainServiceToDb(MainServiceDto mainServiceDto) {
        Optional<MainServices> mainServiceOptional = mainServicesService.getMainServiceDao().findByGroupName(mainServiceDto.getGroupName());
        if (mainServiceOptional.isEmpty()) {
            MainServices mainServices = mainServicesService.getMainServiceMapper().toEntity(mainServiceDto);
            mainServicesService.getMainServiceDao().save(mainServices);
        } else {
            throw new MainServiceDuplicateException();
        }
    }

    public List<UserDto> getListUsers() {
        List<User> users = userService.getUserDao().findAll();
        return users.stream().map(userService.getUserMapper()::toDto).collect(Collectors.toList());
    }

    public List<CustomerDto> getListCustomerNoConfirm() {
        List<Customer> customers = customerService.getCustomerDao().findByStateEquals(UserState.NOT_CONFIRMED);
        return customers.stream().map(customerService.getCustomerMapper()::toDto).collect(Collectors.toList());
    }

    public List<UserDto> getListUserNoConfirm() {
        List<User> users = userService.getUserDao().findByStateEquals(UserState.NOT_CONFIRMED);
        return users.stream().map(userService.getUserMapper()::toDto).collect(Collectors.toList());
    }

    public void confirmCustomer(CustomerDto customerDto) {
        Customer customer = customerService.getCustomerByEmail(customerDto.getEmail());
        if (customer != null) {
            customer.setState(UserState.CONFIRMED);
            customerService.getCustomerDao().save(customer);
        } else {
            throw new CustomerNotExistException();
        }

    }

    public void confirmUser(int id) {
        User user = userService.getUserById(id);
        if (user != null) {
            user.setState(UserState.CONFIRMED);
            userService.getUserDao().save(user);
        } else {
            throw new CustomerNotExistException();
        }

    }

    public void confirmAll(List<UserDto> userDtos) {
        //List<User> users = userDtos.stream().map(userMapper::toEntity).collect(Collectors.toList());
        //users.forEach(user -> user.setState(UserState.CONFIRMED));
       // userDao.saveAll(users);
        userDtos.forEach(userDto -> confirmUser(userDto.getId()));
    }

    public Manager getManagerByEmailAndPass(String userName, String password) {
        Optional<Manager> managerOptional = managerDao.findByEmailAndPassword(userName, password);
        if (managerOptional.isPresent()) {
            return managerOptional.get();
        } else {
            throw new ManagerNotFoundException();
        }
    }

    public void saveManager(Manager manager) {
        managerDao.save(manager);
    }

    public void updatePassword(String email, String newPassword) {
        Optional<Manager> optionalManager = managerDao.findByEmail(email);
        if (optionalManager.isPresent()) {
            Manager manager = optionalManager.get();
            manager.setPassword(newPassword);
            managerDao.save(manager);
        } else {
            throw new ManagerNotFoundException();
        }
    }
}
