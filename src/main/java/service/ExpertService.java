package service;

import dao.ExpertDao;
import dao.OfferDao;
import dao.OrderDao;
import dao.SubServiceDao;
import exceptions.InvalidTimeException;
import lombok.Data;
import model.Expert;
import model.Offer;
import model.Orders;
import model.SubServices;
import model.enums.UserState;
import service.validation.UserAndPassValidation;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

@Data
public class ExpertService {
    ExpertDao expertDao = new ExpertDao();//AnnotationConfigApplicationContext(SpringConfig.class).getBean(ExpertDao.class);
    OrderDao orderDao = new OrderDao();
    SubServiceDao subServiceDao = new SubServiceDao();
    OfferDao offerDao=new OfferDao();

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

    public void setImage(File image, String email) {
        byte[] imageFile = new byte[(int) image.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(image);
            fileInputStream.read(imageFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Expert expert = getExpertByEmail(email);
        expert.setImage(imageFile);
        expertDao.update(expert);

    }

    public void updateExpert(Expert expert) {
        expertDao.update(expert);
    }

    public List<Orders> getListOrders(Expert expert) {
        return orderDao.getListOrders(expert);
    }

    public void addSubServiceToExpertList(Expert expert, String subService) {
        SubServices subServices = subServiceDao.getSubServiceByName(subService);
        if (subServices != null) {
            expertDao.UpdateExpertServicesByEmail(expert.getEmail(), subServices);
        } else {
            throw new RuntimeException("this subService not found");
        }
    }

    public void deleteSubServiceFromExpert(Expert expert, String subService) {
        SubServices subServices = subServiceDao.getSubServiceByName(subService);
        if (subServices != null) {
            expertDao.deleteServiceFromExpert(expert.getEmail(), subServices);
        } else {
            throw new RuntimeException("this subService not found");
        }

    }

    public void addOfferToOrder(Expert expert,Orders orders, double price, Date date, int time,int startTime){
        try {
            if (UserAndPassValidation.isValidTime(time)) {
                Offer offer = Offer.OfferBuilder.anOffer()
                        .withOfferPrice(price)
                        .withOfferCreateDate(date)
                        .withDoneTime(time)
                        .withStartTime(startTime)
                        .withExpert(expert)
                        .withOrders(orders)
                        .build();
                offerDao.save(offer);
                expert.setOffer(offer);
                expertDao.update(expert);
                orderDao.addOfferToOrder(orders,offer);
            }
        }catch (InvalidTimeException e){
            e.printStackTrace();
        }
    }
}
