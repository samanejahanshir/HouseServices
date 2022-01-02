package service;

import dao.MainServiceDao;
import dao.ManagerDao;
import dao.ServicesDao;
import model.MainServices;
import model.SubServices;

public class ManagerService {
    ManagerDao managerDao = new ManagerDao();
    ServicesDao servicesDao = new ServicesDao();
    MainServiceDao mainServiceDao = new MainServiceDao();

    public void addServicesToDb(SubServices subServices) {
        if (mainServiceDao.getMainService(subServices.getGroupService()) != null) {
            if (servicesDao.getService(subServices.getGroupService(), subServices.getSubService()) == null) {
                servicesDao.save(subServices);
            } else {
                throw new RuntimeException("this services is exist .");
            }
        } else {
            throw new RuntimeException("this Main service not exist");
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

    public void saveMainServiceToDb(MainServices mainServices){
        if (mainServiceDao.getMainService(mainServices.getGroupName())==null) {
            mainServiceDao.save(mainServices);
        }else {
            throw new RuntimeException("this mainService is exist");
        }
    }
}
