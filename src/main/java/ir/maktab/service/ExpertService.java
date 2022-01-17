package ir.maktab.service;

import ir.maktab.data.dao.*;
import ir.maktab.data.enums.OfferState;
import ir.maktab.data.enums.OrderState;
import ir.maktab.data.model.Expert;
import ir.maktab.data.model.Offer;
import ir.maktab.data.model.Orders;
import ir.maktab.data.model.SubServices;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.OfferDto;
import ir.maktab.dto.mapper.ExpertMapper;
import ir.maktab.dto.mapper.OfferMapper;
import ir.maktab.dto.mapper.OrderMapper;
import ir.maktab.exceptions.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    // final OfferService offerService;
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
            throw new ExpertNotExistException();
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
        Expert expertByEmail = getExpertByEmail(expert.getEmail());
        if (expertByEmail != null) {
            expertDao.save(expert);
        } else {
            throw new ExpertNotExistException();
        }
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
            throw new SubServiceNotFoundException();
        }
    }

    //TODO
    @Transactional
    public void deleteSubServiceFromExpert(String email, String subService) {
        Optional<SubServices> subServicesOptional = subServiceDao.findByName(subService);
        Expert expert = getExpertByEmail(email);
        if (subServicesOptional.isPresent() && expert != null) {
            expert.getServices().remove(subServicesOptional.get());
            expertDao.save(expert);
        } else {
            throw new SubServiceNotFoundException();
        }
    }

    @Transactional
    public void addOfferToOrder(String email, OfferDto offerDto) {
        Expert expert = getExpertByEmail(email);
        if (expert != null) {
            // Optional<Offer> offerOptional=offerDao.getOfferByCondition(offerDto.getOrderDto().getOrderDoingDate(),startTime);
            // if(offerOptional.isEmpty()){
            List<Offer> offers = offerDao.getListOfferByExpertId(expert.getId());
            if (offers.stream().filter(offer -> offer.getOrders().getOrderDoingDate().equals(offerDto.getOrderDto().getOrderDoingDate()) && offer.getStartTime() + offer.getDurationTime() > offerDto.getStartTime()
            ).findFirst().isEmpty()) {
                if (offerDto.getOfferPrice() >= offerDto.getOrderDto().getSubServiceDto().getBasePrice()) {
                    offerDto.setExpertDto(expertMapper.toDto(expert));
                    Offer offer = offerMapper.toEntity(offerDto);
                    offer.setState(OfferState.NEW);
                    offerDao.save(offer);
                    Orders order = orderMapper.toEntity(offerDto.getOrderDto());
                    order.setState(OrderState.WAIT_SELECT_EXPERT);
                    orderDao.save(order);
                } else {
                    throw new InvalidPriceException();
                }
            } else {
                throw new OfferConflictByOtherException();
            }
        }
    }

    public void updateOrderState(int idOrder, OrderState state) {
        orderDao.updateOrderState(idOrder, state);
    }


}
