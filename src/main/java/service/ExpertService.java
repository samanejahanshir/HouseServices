package service;

import data.model.Expert;
import data.model.Orders;
import data.model.SubServices;
import data.dao.*;
import data.enums.OrderState;
import data.enums.UserState;
import exceptions.InvalidSizeImageException;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class ExpertService {
    ExpertDao expertDao;
    OrderDao orderDao;
    SubServiceDao subServiceDao;
    OfferDao offerDao;
    CustomerDao customerDao;

    public ExpertService(ExpertDao expertDao, OrderDao orderDao, SubServiceDao subServiceDao, OfferDao offerDao,CustomerDao customerDao) {
        this.expertDao = expertDao;
        this.orderDao = orderDao;
        this.subServiceDao = subServiceDao;
        this.offerDao = offerDao;
        this.customerDao=customerDao;
    }

    public void saveExpert(Expert expert) {
        if (expertDao.findByEmail(expert.getEmail()).isEmpty()) {
            expert.setState(UserState.NOT_CONFIRMED);
            expertDao.save(expert);
        } else {
            throw new RuntimeException("this expert by this email is exist .");
        }
    }

    public Expert getExpertByEmailAndPass(String email, String password) {
        Optional<Expert> expertOptional=expertDao.findByEmailAndPassword(email, password);
        if(expertOptional.isPresent()){
          return  expertOptional.get();
        }else {
            throw new RuntimeException("this expert by this email and pass not exist");
        }
    }

    public Expert getExpertByEmail(String email) {
        Optional<Expert> expertOptional=expertDao.findByEmail(email);
        if(expertOptional.isPresent()){
            return  expertOptional.get();
        }else {
            throw new RuntimeException("this expert by this email not exist");
        }
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
            //TODO    expertDao.update(expert);
            } else {
                throw new InvalidSizeImageException("this image is big please upload anyImage");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateExpert(Expert expert) {
        //TODO expertDao.update(expert);
    }

    public List<Orders> getListOrdersOfSubServiceExpert(String email) {
        Expert expert=getExpertByEmail(email);
        return orderDao.getListOrdersOfSubServiceExpert(expert.getId());
    }

    //TODO  delete subservice from expert model
    public void addSubServiceToExpertList(String email, String subService) {
        Expert expert=getExpertByEmail(email);
        Optional<SubServices> subServicesOptional = subServiceDao.getSubServiceByName(subService);
        if (subServicesOptional.isPresent() && expert!=null) {
            SubServices subServices=subServicesOptional.get();
            subServices.getExperts().add(expert);
          //TODO  subServiceDao.update(subServices);
        } else {
            throw new RuntimeException("this subService not found");
        }
    }

    //TODO
    public void deleteSubServiceFromExpert(Expert expert, String subService) {
        Optional<SubServices> subServicesOptional = subServiceDao.getSubServiceByName(subService);
        if (subServicesOptional.isPresent()) {
          //TODO  expertDao.deleteServiceFromExpert(expert.getEmail(), subServices);
        } else {
            throw new RuntimeException("this subService not found");
        }
    }

   /* public void addOfferToOrder(Expert expert, Orders orders, double price, int time, int startTime) {
        try {
            if (CheckValidation.isValidTime(time)) {
                Offer offer = Offer.OfferBuilder.anOffer()
                        .withOfferPrice(price)
                        .withDoneTime(time)
                        .withStartTime(startTime)
                        .withExpert(expert)
                        .withOrders(orders)
                        .build();
                offerDao.save(offer);
                orders.getOffers().add(offer);
                orderDao.update(orders);
            }
        } catch (InvalidTimeException e) {
            e.printStackTrace();
        }
    }*/

    public List<Orders> getOrdersWaitForSelectExpert(Expert expert) {
        return orderDao.updateOrderStateToComeExpert(expert.getId());
    }

    public int updateOrderState(int idOrder, OrderState state) {
        return orderDao.updateOrderState(idOrder, state);
    }

    public int updateOrderStateToPaid(int orderId) {
        Optional<Orders> orders = orderDao.findById(orderId);
        int result = 0;
        if(orders.isPresent()) {
            Orders order=orders.get();
            try {
                if (orders != null) {
                    if (order.getCustomer().getCredit() >= order.getProposedPrice()) {
                        order.getCustomer().setCredit(order.getCustomer().getCredit() - order.getProposedPrice());
                     //TODO   customerDao.update(order.getCustomer());
                        order.getExpert().setCredit(order.getExpert().getCredit() + order.getProposedPrice());
                     //   expertDao.update(order.getExpert());
                        result = orderDao.updateOrderState(orderId, OrderState.PAID);
                    } else {
                        throw new RuntimeException("credit of customer is not enough.");
                    }
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
