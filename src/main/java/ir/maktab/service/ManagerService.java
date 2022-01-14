package ir.maktab.service;

import ir.maktab.data.dao.*;
import ir.maktab.data.enums.UserState;
import ir.maktab.data.model.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class ManagerService {
    private final ManagerDao managerDao;
    private final SubServiceDao servicesDao;
    private final MainServiceDao mainServiceDao;
    private final ExpertDao expertDao;
    private final UserDao userDao;

    @Autowired
    public ManagerService(ManagerDao managerDao, SubServiceDao servicesDao, MainServiceDao mainServiceDao, UserDao userDao, ExpertDao expertDao) {
        this.managerDao = managerDao;
        this.servicesDao = servicesDao;
        this.mainServiceDao = mainServiceDao;
        this.userDao = userDao;
        this.expertDao = expertDao;
    }

    public void addServicesToDb(SubServices subServices) {
        try {
            if (mainServiceDao.findByGroupName(subServices.getGroupName()).isPresent()) {
                if (servicesDao.findByNameAndGroupName(subServices.getGroupName(), subServices.getName()).isEmpty()) {
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

    @Transactional
    public void deleteMainServices(String groupName) {
        List<SubServices> subServicesList = servicesDao.findAllByGroupName(groupName);
        List<Expert> experts = expertDao.getListExpertByGroupName(groupName);
        for (Expert expert : experts) {
            expert.getServices().removeAll(expert.getServices().stream().filter(subServices -> subServices.getGroupName().equals(groupName)).collect(Collectors.toList()));
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
            throw new RuntimeException("this subService not found");
        }
    }

    public void saveMainServiceToDb(MainServices mainServices) {
        try {
            if (mainServiceDao.findByGroupName(mainServices.getGroupName()).isEmpty()) {
                mainServiceDao.save(mainServices);
            } else {
                throw new RuntimeException("this mainService is exist");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public List<User> getListUsers() {
        return userDao.findAll();

    }

   /* public List<User> getListUsersByCondition(UserType type, String email, String name, String family) {
        return userDao.getListUserByCondition(type, email, name, family);

    }*/

    public List<User> getListUserNoConfirm() {
        return userDao.getListUserNoConfirm();
    }

    public void confirmUser(User user) {
        user.setState(UserState.CONFIRMED);
        userDao.save(user);
    }

    public Manager getManagerByNameAndPass(String userName, String password) {
        Optional<Manager> managerOptional = managerDao.findByUserNameAndPassword(userName, password);
        if (managerOptional.isPresent()) {
            return managerOptional.get();
        } else {
            throw new RuntimeException("this manager by this userName and password not exist");
        }
    }

    public void saveManager(Manager manager) {
        managerDao.save(manager);
    }
}
