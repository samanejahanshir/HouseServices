package service;

import dao.MainServiceDao;
import dao.ManagerDao;
import dao.SubServiceDao;
import dao.UserDao;
import model.MainServices;
import model.Manager;
import model.SubServices;
import model.User;
import model.enums.UserState;
import model.enums.UserType;

import java.util.List;

public class ManagerService {
    ManagerDao managerDao = new ManagerDao();
    SubServiceDao servicesDao = new SubServiceDao();
    MainServiceDao mainServiceDao = new MainServiceDao();
    UserDao userDao = new UserDao();

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
        }catch (RuntimeException e){
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
        }catch (RuntimeException e){
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

    public Manager getManagerByNameAndPass(String userName,String password){
       return managerDao.get(userName,password);
    }

    public void saveManager(Manager manager){
        managerDao.save(manager);
    }
}
