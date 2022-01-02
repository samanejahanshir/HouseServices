package service;

import dao.ExpertDao;
import lombok.Data;
import model.Expert;

@Data
public class ExpertService {
    ExpertDao expertDao = new ExpertDao();//AnnotationConfigApplicationContext(SpringConfig.class).getBean(ExpertDao.class);

    public void saveExpert(Expert expert) {
        if(expertDao.getExpertByEmail(expert.getEmail())==null) {
            expertDao.save(expert);
        }else{
            throw  new RuntimeException("this expert by this email is exist .");
        }
    }

    public Expert getExpertByEmail(String email, String password) {
        return  expertDao.getExpertByEmailAndPass(email, password);
    }

    public int updatePassword(String email,String newPassword){
        return expertDao.UpdatePassword(email,newPassword);
    }
}
