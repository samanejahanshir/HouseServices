package ir.maktab.service;

import ir.maktab.data.dao.*;
import ir.maktab.data.enums.OrderState;
import ir.maktab.data.enums.UserState;
import ir.maktab.data.model.Expert;
import ir.maktab.data.model.Offer;
import ir.maktab.data.model.Orders;
import ir.maktab.data.model.SubServices;
import ir.maktab.exceptions.InvalidSizeImageException;
import ir.maktab.exceptions.InvalidTimeException;
import ir.maktab.service.validation.CheckValidation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
@Data
public class ExpertService {
    ExpertDao expertDao;
    OrderDao orderDao;
    SubServiceDao subServiceDao;
    OfferDao offerDao;
    CustomerDao customerDao;

  /*  public ExpertService(ExpertDao expertDao, OrderDao orderDao, SubServiceDao subServiceDao, OfferDao offerDao, CustomerDao customerDao) {
        this.expertDao = expertDao;
        this.orderDao = orderDao;
        this.subServiceDao = subServiceDao;
        this.offerDao = offerDao;
        this.customerDao = customerDao;
    }
*/
    public void saveExpert(Expert expert) {
        if (expertDao.findByEmail(expert.getEmail()).isEmpty()) {
            expertDao.save(expert);
        } else {
            throw new RuntimeException("this expert by this email is exist .");
        }
    }

    public Expert getExpertByEmailAndPass(String email, String password) {
        Optional<Expert> expertOptional = expertDao.findByEmailAndPassword(email, password);
        if (expertOptional.isPresent()) {
            return expertOptional.get();
        } else {
            throw new RuntimeException("this expert by this email and pass not exist");
        }
    }

    public Expert getExpertByEmail(String email) {
        Optional<Expert> expertOptional = expertDao.findByEmail(email);
        if (expertOptional.isPresent()) {
            return expertOptional.get();
        } else {
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
                expertDao.save(expert);
            } else {
                throw new InvalidSizeImageException("this image is big please upload anyImage");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Expert getExpertByEmailJoinSubService(String email) {
        Optional<Expert> expertOptional = expertDao.getExpertByEmailJoinSubService(email);
        if (expertOptional.isPresent()) {
            return expertOptional.get();
        } else {
            throw new RuntimeException("this expert by this email not exist");
        }
    }

    public void updateExpert(Expert expert) {
        expertDao.save(expert);
    }

    @Transactional
    public List<Orders> getListOrdersOfSubServiceExpert(String email) {
        Expert expert = getExpertByEmail(email);
        List<String> subServiceNames = expert.getServices().stream().map(subServices -> subServices.getName()).collect(Collectors.toList());
        return orderDao.getListOrdersOfSubServiceExpert(subServiceNames);
    }

    @Transactional
    public void addSubServiceToExpertList(String email, String subService) {
        Expert expert = getExpertByEmail(email);
        Optional<SubServices> subServicesOptional = subServiceDao.findByName(subService);
        if (subServicesOptional.isPresent() && expert != null) {
            SubServices subServices = subServicesOptional.get();
            expert.getServices().add(subServices);
            expertDao.save(expert);
        } else {
            throw new RuntimeException("this subService not found");
        }
    }

    //TODO
    public void deleteSubServiceFromExpert(String email, String subService) {
        Optional<SubServices> subServicesOptional = subServiceDao.findByName(subService);
        Expert expert = getExpertByEmailJoinSubService(email);
        if (subServicesOptional.isPresent() && expert != null) {
            expert.getServices().remove(subServicesOptional.get());
            expertDao.save(expert);
        } else {
            throw new RuntimeException("this subService not found");
        }
    }

    @Transactional
    public void addOfferToOrder(String email, Orders orders, double price, int time, int startTime) {
        Expert expert = getExpertByEmail(email);
        if (expert != null) {
          /* Optional<Offer> offerOptional=offerDao.getOfferByCondition(orders.getOrderDoingDate(),startTime);
           if(offerOptional.isEmpty()){*/
            try {
                if (CheckValidation.isValidTime(startTime)) {
                    List<Offer> offers = offerDao.getListOfferByExpertId(expert.getId());
                    //TODO
                    if (offers.stream().filter(offer -> offer.getOrders().getOrderDoingDate().equals(orders.getOrderDoingDate()) && offer.getStartTime() + offer.getDurationTime() > startTime
                    ).findFirst().isEmpty()) {
                        if (price >= orders.getSubServices().getBasePrice()) {
                            Offer offer = Offer.builder()
                                    .offerPrice(price)
                                    .durationTime(time)
                                    .startTime(startTime)
                                    .expert(expert)
                                    .orders(orders)
                                    .build();
                            offerDao.save(offer);
                            orders.setState(OrderState.WAIT_SELECT_EXPERT);
                            orderDao.save(orders);
                        } else {
                            throw new RuntimeException("price should be bigger than basePrice of subService");
                        }
                    } else {
                        throw new RuntimeException("there is a offer by this date and time");
                    }
                }
            } catch (InvalidTimeException e) {
                e.printStackTrace();
            }

        }
    }

    public List<Orders> getListOrdersForExpert(Expert expert) {
        return orderDao.getListOrdersForExpert(expert.getId());
    }

    public int updateOrderState(int idOrder, OrderState state) {
        return orderDao.updateOrderState(idOrder, state);
    }

    @Transactional
    public int updateOrderStateToPaid(int orderId) {
        Optional<Orders> orders = orderDao.findById(orderId);
        int result = 0;
        if (orders.isPresent()) {
            Orders order = orders.get();
            try {
                if (order.getCustomer().getCredit() >= order.getProposedPrice()) {
                    order.getCustomer().setCredit(order.getCustomer().getCredit() - order.getProposedPrice());
                    customerDao.save(order.getCustomer());
                    result = orderDao.updateOrderState(orderId, OrderState.PAID);
                } else {
                    throw new RuntimeException("credit of customer is not enough.");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
