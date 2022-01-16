package ir.maktab.dto.mapper;

import ir.maktab.data.model.Offer;
import ir.maktab.dto.OfferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class OfferMapper {
    private  final ExpertMapper expertMapper;
    private  final OrderMapper orderMapper;

    public OfferDto toDto(Offer offer) {
        return OfferDto.builder()
                .id(offer.getId())
                .offerCreateDate(offer.getOfferCreateDate())
                .offerPrice(offer.getOfferPrice())
                .durationTime(offer.getDurationTime())
                .startTime(offer.getStartTime())
                .expertDto(expertMapper.toDto(offer.getExpert()))
                .orderDto(orderMapper.toDto(offer.getOrders()))
                .state(offer.getState())
                .build();
    }
    public Offer toEntity(OfferDto offerDto){
        return  Offer.builder()
                .offerPrice(offerDto.getOfferPrice())
                .durationTime(offerDto.getDurationTime())
                .id(offerDto.getId())
                .offerCreateDate(offerDto.getOfferCreateDate())
                .startTime(offerDto.getStartTime())
                .expert(expertMapper.toEntity(offerDto.getExpertDto()))
                .orders(orderMapper.toEntity(offerDto.getOrderDto()))
                .state(offerDto.getState())
                .build();

    }
}
