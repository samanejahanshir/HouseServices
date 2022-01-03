package service;

import lombok.Data;
import model.Customer;
import model.Expert;
import model.User;
import model.UserFactory;
import model.enums.UserType;

@Data
public class HomeServicesService {
public void register(String name, String family, String email, String password, UserType type){

    User user= UserFactory.getUser(type,name,family,email,password);
    if(user instanceof Customer){
        CustomerService customerService=new CustomerService();
        customerService.saveCustomer((Customer)user);
    }
    else if(user instanceof Expert){
        ExpertService expertService=new ExpertService();
        expertService.saveExpert((Expert) user);
    }
}
}
