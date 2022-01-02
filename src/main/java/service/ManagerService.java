package service;

import dao.ManagerDao;
import dao.ServicesDao;
import model.Services;

public class ManagerService {
    ManagerDao managerDao = new ManagerDao();
    ServicesDao servicesDao = new ServicesDao();
    public void addServicesToDb(Services services){
        if(servicesDao.getService(services.getGroupService(),services.getSubService())==null) {
            servicesDao.save(services);
        }else {
            throw new RuntimeException("this services is exist .");
        }
    }
    public int deleteServices(String groupName){
        //TODO delete from expert list
       return servicesDao.deleteOneServices(groupName);
    }
    public int deleteSubServices(String subServices){
        //TODO delete from expert list
        return servicesDao.deleteOneSubServices(subServices);
    }
}
