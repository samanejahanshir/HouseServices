package service;

import dao.*;
import exceptions.InvalidSizeImageException;
import exceptions.InvalidTimeException;
import lombok.Data;
import model.Expert;
import model.Offer;
import model.Orders;
import model.SubServices;
import model.enums.OrderState;
import model.enums.UserState;
import service.validation.CheckValidation;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

@Data
public class ExpertService {
    ExpertDao expertDao = new ExpertDao();
    OrderDao orderDao = new OrderDao();
    SubServiceDao subServiceDao = new SubServiceDao();
    OfferDao offerDao = new OfferDao();

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
        int maxsize = 300000;
        byte[] imageFile = new byte[(int) image.length()];
        try {
            if (imageFile.length <= maxsize) {
                FileInputStream fileInputStream = new FileInputStream(image);
                fileInputStream.read(imageFile);
                fileInputStream.close();
                Expert expert = getExpertByEmail(email);
                expert.setImage(imageFile);
                expertDao.update(expert);
            } else {
                throw new InvalidSizeImageException("this image is big please upload anyImage");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void addOfferToOrder(Expert expert, Orders orders, double price, Date date, int time, int startTime) {
        try {
            if (CheckValidation.isValidTime(time)) {
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
                orderDao.addOfferToOrder(orders, offer);
            }
        } catch (InvalidTimeException e) {
            e.printStackTrace();
        }
    }

    public List<Orders> getOrdersWaitForSelectExpert(Expert expert) {
        return orderDao.updateOrderStateToComeExpert(expert);
    }

    public int updateOrderState(int idOrder, OrderState state) {
        return orderDao.updateOrderState(idOrder, state);
    }

    public int updateOrderStateToPaid(int orderId){
        Orders orders=orderDao.getOrderById(orderId);
        int result=0;
        try {
            if (orders != null) {
                if (orders.getCustomer().getCredit() >= orders.getProposedPrice()) {
                    orders.getCustomer().setCredit(orders.getCustomer().getCredit() - orders.getProposedPrice());
                    CustomerDao customerDao = new CustomerDao();
                    customerDao.update(orders.getCustomer());
                    orders.getExpert().setCredit(orders.getExpert().getCredit() + orders.getProposedPrice());
                    expertDao.update(orders.getExpert());
                    result = orderDao.updateOrderState(orderId, OrderState.PAID);
                } else {
                    throw new RuntimeException("credit of customer is not enough.");
                }
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return  result;
    }
}
