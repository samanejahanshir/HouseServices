package service;

import dao.ExpertDao;
import dao.OrderDao;
import lombok.Data;
import model.Expert;
import model.Orders;
import model.enums.UserState;

import java.util.List;

@Data
public class ExpertService {
    ExpertDao expertDao = new ExpertDao();//AnnotationConfigApplicationContext(SpringConfig.class).getBean(ExpertDao.class);
    OrderDao orderDao = new OrderDao();

    public void saveExpert(Expert expert) {
        if (expertDao.getExpertByEmail(expert.getEmail()) == null) {
            expert.setState(UserState.NOT_CONFIRMED);
            expertDao.save(expert);
        } else {
            throw new RuntimeException("this expert by this email is exist .");
        }
    }

    public Expert getExpertByEmailAndPass(String email, String password) {
        return expertDao.getExpertByEmailAndPass(email, password);
    }

    public Expert getExpertByEmail(String email) {
        return expertDao.getExpertByEmail(email);
    }

    public int updatePassword(String email, String newPassword) {
        return expertDao.UpdatePassword(email, newPassword);
    }

    public List<Orders> getListOrders(Expert expert) {
        return orderDao.getListOrders(expert);
    }
}
