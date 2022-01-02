package config;

import dao.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import service.CustomerService;
import service.ExpertService;
import service.UserService;

@Configuration
@ComponentScan(basePackages = {"dao", "service"})
public class SpringConfig {
    @Bean
    public CustomerService customerService(CustomerDao customerDao) {
        CustomerService customerService = new CustomerService();
        customerService.setCustomerDao(customerDao);
        return customerService;
    }

    @Bean
    public ExpertService expertService(ExpertDao expertDao) {
        ExpertService expertService = new ExpertService();
        expertService.setExpertDao(expertDao);
        return expertService;
    }

    @Bean
    public UserService userService(UserDao userDao) {
        UserService userService = new UserService();
        userService.setUserDao(userDao);
        return userService;
    }

    @Bean
    public ExpertDao expertDao() {
        ExpertDao expertDao = new ExpertDao();
        return expertDao;
    }

    @Bean
    public ManagerDao managerDao() {
        ManagerDao managerDao = new ManagerDao();
        return managerDao;
    }

    @Bean
    public OrderDao orderDao() {
        OrderDao orderDao = new OrderDao();
        return orderDao;
    }

    @Bean
    public SubServiceDao servicesDao() {
        SubServiceDao servicesDao = new SubServiceDao();
        return servicesDao;
    }

    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        return userDao;
    }

    @Bean
    public CustomerDao customerDao() {
        CustomerDao customerDao = new CustomerDao();
        return customerDao;
    }
}
