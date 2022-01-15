package ir.maktab.service;

import ir.maktab.data.dao.OfferDao;
import ir.maktab.data.dao.SubServiceDao;
import ir.maktab.data.model.Offer;
import ir.maktab.data.model.Orders;
import ir.maktab.dto.OfferDto;
import ir.maktab.dto.OrderDto;
import ir.maktab.dto.mapper.OfferMapper;
import ir.maktab.dto.mapper.OrderMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Data
public class OfferService {
    private final SubServiceDao subServiceDao;
    private final CustomerService customerService;
    private final OfferMapper offerMapper;
    private final OrderMapper orderMapper;
    private final OfferDao offerDao;

    public List<OfferDto> getListOffers(OrderDto orderDto) {
        List<Offer> listOffers = offerDao.getListOffers(orderDto.getId());
        return listOffers.stream().map(offerMapper::toDto).collect(Collectors.toList());
    }

    public List<OfferDto> getListOffersSortByScoreOrPrice(OrderDto orderDto, boolean byPrice, boolean byScoreExpert) {
        List<Offer> listOffers = new ArrayList<>();
        if (byPrice && !byScoreExpert) {
            listOffers = offerDao.getListOffersBySort(orderDto.getId(), Sort.by("offerPrice").ascending());
            return listOffers.stream().map(offerMapper::toDto).collect(Collectors.toList());
        } else if (!byPrice && byScoreExpert) {
            listOffers = offerDao.getListOffersBySort(orderDto.getId(), Sort.by("expert.score").descending());
            return listOffers.stream().map(offerMapper::toDto).collect(Collectors.toList());
        } else if (byPrice && byScoreExpert) {
            Sort offerPriceAndScore = Sort.by("expert.score").descending().and(Sort.by("offerPrice").ascending());
            listOffers = offerDao.getListOffersBySort(orderDto.getId(), offerPriceAndScore);
            return listOffers.stream().map(offerMapper::toDto).collect(Collectors.toList());
        } else {
            return getListOffers(orderDto);
        }
    }
}
