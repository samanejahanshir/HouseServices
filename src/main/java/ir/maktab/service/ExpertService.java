package ir.maktab.service;

import ir.maktab.data.dao.*;
import ir.maktab.data.enums.OrderState;
import ir.maktab.data.enums.UserState;
import ir.maktab.data.model.Expert;
import ir.maktab.data.model.Offer;
import ir.maktab.data.model.Orders;
import ir.maktab.data.model.SubServices;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.OfferDto;
import ir.maktab.dto.OrderDto;
import ir.maktab.dto.mapper.ExpertMapper;
import ir.maktab.dto.mapper.OfferMapper;
import ir.maktab.dto.mapper.OrderMapper;
import ir.maktab.exceptions.ExpertNotExistException;
import ir.maktab.exceptions.InvalidSizeImageException;
import ir.maktab.exceptions.InvalidTimeException;
import ir.maktab.exceptions.UserByEmailExistException;
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
    final ExpertDao expertDao;
    final OrderDao orderDao;
    final SubServiceDao subServiceDao;
    final OfferDao offerDao;
    final CustomerDao customerDao;
    final ExpertMapper expertMapper;
    final OfferMapper offerMapper;
final OrderMapper orderMapper;
    public void saveExpert(Expert expert) {
        if (expertDao.findByEmail(expert.getEmail()).isEmpty()) {
            expertDao.save(expert);
        } else {
            throw new UserByEmailExistException();
        }
    }

    public ExpertDto findByEmailAndPass(String email, String password) {
        Optional<Expert> expertOptional = expertDao.findByEmailAndPassword(email, password);
        if (expertOptional.isPresent()) {
            Expert expert = expertOptional.get();
            return expertMapper.toDto(expert);
        } else {
            throw new ExpertNotExistException();
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

    public void updatePassword(String email, String newPassword) {
        Expert expert = getExpertByEmail(email);
        expert.setPassword(newPassword);
        expertDao.save(expert);
    }

  /*  public void setImage(File image, String email) {
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
    }*/

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
    public void addOfferToOrder(String email, OrderDto orderDto, OfferDto offerDto) {
        Expert expert = getExpertByEmail(email);
        if (expert != null) {
          /* Optional<Offer> offerOptional=offerDao.getOfferByCondition(orders.getOrderDoingDate(),startTime);
           if(offerOptional.isEmpty()){*/
            try {
                List<Offer> offers = offerDao.getListOfferByExpertId(expert.getId());
                if (offers.stream().filter(offer -> offer.getOrders().getOrderDoingDate().equals(orderDto.getOrderDoingDate()) && offer.getStartTime() + offer.getDurationTime() > offerDto.getStartTime()
                ).findFirst().isEmpty()) {
                    if (offerDto.getOfferPrice() >= orderDto.getSubService().getBasePrice()) {
                        Offer offer = offerMapper.toEntity(offerDto);
                        offerDao.save(offer);
                        orderDto.setState(OrderState.WAIT_SELECT_EXPERT);
                        orderDao.save(orderMapper.toEntity(orderDto));
                    } else {
                        throw new RuntimeException("price should be bigger than basePrice of subService");
                    }
                } else {
                    throw new RuntimeException("there is a offer by this date and time");
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
