package service;

import config.SpringConfig;
import dao.ExpertDao;
import lombok.Data;
import model.Expert;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Data
public class ExpertService {
    ExpertDao expertDao = new ExpertDao();//AnnotationConfigApplicationContext(SpringConfig.class).getBean(ExpertDao.class);

    public void saveExpert(Expert expert) {
        expertDao.save(expert);
    }

    public Expert getExpertByEmail(String email, String password) {
        return  expertDao.getExpertByEmail(email, password);
    }

    public int updatePassword(String email,String newPassword){
        return expertDao.UpdatePassword(email,newPassword);
    }
}
