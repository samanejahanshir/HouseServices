package ir.maktab.dto.mapper;

import ir.maktab.data.model.Offer;
import ir.maktab.dto.OfferDto;
import org.springframework.stereotype.Component;

@Component
public class OfferMapper {
    public OfferDto toDto(Offer offer) {
        return OfferDto.builder()
                .id(offer.getId())
                .offerCreateDate(offer.getOfferCreateDate())
                .offerPrice(offer.getOfferPrice())
                .durationTime(offer.getDurationTime())
                .startTime(offer.getStartTime())
                .expert(offer.getExpert())
                .build();
    }
    public Offer toEntity(OfferDto offerDto){
        return  Offer.builder()
                .offerPrice(offerDto.getOfferPrice())
                .durationTime(offerDto.getDurationTime())
                .id(offerDto.getId())
                .offerCreateDate(offerDto.getOfferCreateDate())
                .startTime(offerDto.getStartTime())
                .expert(offerDto.getExpert())
                .build();

    }
}
