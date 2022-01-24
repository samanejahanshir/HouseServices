package ir.maktab.service;

import ir.maktab.data.dao.OfferDao;
import ir.maktab.data.dao.SubServiceDao;
import ir.maktab.data.enums.OrderState;
import ir.maktab.data.model.Expert;
import ir.maktab.data.model.Offer;
import ir.maktab.dto.OfferDto;
import ir.maktab.dto.OrderDto;
import ir.maktab.dto.mapper.OfferMapper;
import ir.maktab.exceptions.InvalidPriceException;
import ir.maktab.exceptions.OfferNotFound;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Data
public class OfferService {
    final SubServiceDao subServiceDao;
    final CustomerService customerService;
    final OfferMapper offerMapper;
    final OrderService orderService;
    final OfferDao offerDao;
    final ExpertService expertService;

    @Transactional
    public List<OfferDto> getListOffers(OrderDto orderDto) {
        List<Offer> listOffers = offerDao.findByOrders_Id(orderDto.getId());
        return listOffers.stream().map(offerMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public List<OfferDto> getListOffersSortByScoreOrPrice(OrderDto orderDto, boolean byPrice, boolean byScoreExpert) {
        List<Offer> listOffers = new ArrayList<>();
        if (byPrice && !byScoreExpert) {
            listOffers = offerDao.findByOrders_Id(orderDto.getId(), Sort.by("offerPrice").ascending());
            return listOffers.stream().map(offerMapper::toDto).collect(Collectors.toList());
        } else if (!byPrice && byScoreExpert) {
            listOffers = offerDao.findByOrders_Id(orderDto.getId(), Sort.by("expert.score").descending());
            return listOffers.stream().map(offerMapper::toDto).collect(Collectors.toList());
        } else if (byPrice && byScoreExpert) {
            Sort offerPriceAndScore = Sort.by("expert.score").descending().and(Sort.by("offerPrice").ascending());
            listOffers = offerDao.findByOrders_Id(orderDto.getId(), offerPriceAndScore);
            return listOffers.stream().map(offerMapper::toDto).collect(Collectors.toList());
        } else {
            return getListOffers(orderDto);
        }
    }

   /* @Transactional
    public void addOfferToOrder(String email, OfferDto offerDto) {
        Expert expert = expertService.getExpertByEmail(email);
        if (expert != null) {
            List<Offer> offers = offerDao.getListOfferByExpertId(expert.getId());
            if (offers.stream().filter(offer -> offer.getOrders().getOrderDoingDate().equals(offerDto.getOrderDto().getOrderDoingDate()) && offer.getStartTime() + offer.getDurationTime() > offerDto.getStartTime()
            ).findFirst().isEmpty()) {
                if (offerDto.getOfferPrice() >= offerDto.getOrderDto().getSubServiceDto().getBasePrice()) {
                    offerDto.setExpertDto(expertService.expertMapper.toDto(expert));
                    Offer offer = offerMapper.toEntity(offerDto);
                    offerDao.save(offer);
                    offerDto.getOrderDto().setState(OrderState.WAIT_SELECT_EXPERT);
                    orderService.orderDao.save(orderService.orderMapper.toEntity(offerDto.getOrderDto()));
                } else {
                    throw new InvalidPriceException();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }*/

    public OfferDto findOfferById(int id) {
        Optional<Offer> offerOptional = offerDao.findById(id);
        if (offerOptional.isPresent()) {
            return offerMapper.toDto(offerOptional.get());
        } else {
            throw new OfferNotFound();
        }
    }
}
