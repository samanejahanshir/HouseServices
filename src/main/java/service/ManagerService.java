package service;

import dao.MainServiceDao;
import dao.ManagerDao;
import dao.SubServiceDao;
import dao.UserDao;
import data.MainServices;
import data.Manager;
import data.SubServices;
import data.User;
import data.enums.UserState;
import data.enums.UserType;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ManagerService {
    private final ManagerDao managerDao;
    private final SubServiceDao servicesDao;
    private final MainServiceDao mainServiceDao;
    private final UserDao userDao;

    @Autowired
    public ManagerService(ManagerDao managerDao, SubServiceDao servicesDao, MainServiceDao mainServiceDao, UserDao userDao) {
        this.managerDao = managerDao;
        this.servicesDao = servicesDao;
        this.mainServiceDao = mainServiceDao;
        this.userDao = userDao;
    }

    public void addServicesToDb(SubServices subServices) {
        try {
            if (mainServiceDao.getMainService(subServices.getGroupService()) != null) {
                if (servicesDao.getService(subServices.getGroupService(), subServices.getSubService()) == null) {
                    servicesDao.save(subServices);
                } else {
                    throw new RuntimeException("this services is exist .");
                }
            } else {
                throw new RuntimeException("this Main service not exist");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public int deleteServices(String groupName) {
        //TODO delete from expert list
        return servicesDao.deleteOneServices(groupName);
    }

    public int deleteSubServices(String subServices) {
        //TODO delete from expert list
        return servicesDao.deleteOneSubServices(subServices);
    }

    public void saveMainServiceToDb(MainServices mainServices) {
        try {
            if (mainServiceDao.getMainService(mainServices.getGroupName()) == null) {
                mainServiceDao.save(mainServices);
            } else {
                throw new RuntimeException("this mainService is exist");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public List<User> getListUsers() {
        return userDao.getListUser();

    }

    public List<User> getListUsersByCondition(UserType type, String email, String name, String family) {
        return userDao.getListUserByCondition(type, email, name, family);

    }

    public List<User> getListUserNoConfirm() {
        return userDao.getListUserNoConfirm();
    }

    public void confirmUser(User user) {
        user.setState(UserState.CONFIRMED);
        userDao.confirmUser(user);
    }

    public Manager getManagerByNameAndPass(String userName, String password) {
        return managerDao.get(userName, password);
    }

    public void saveManager(Manager manager) {
        managerDao.save(manager);
    }
}
