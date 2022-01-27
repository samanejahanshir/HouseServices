package ir.maktab.dto.mapper;

import ir.maktab.data.model.SubServices;
import ir.maktab.dto.SubServiceDto;
import org.springframework.stereotype.Component;

@Component
public class SubServiceMapper {
    public SubServiceDto toDto(SubServices subServices) {
        return SubServiceDto.builder()
                .id(subServices.getId())
                .name(subServices.getName())
                .basePrice(subServices.getBasePrice())
                .description(subServices.getDescription())
                .groupName(subServices.getMainServices().getGroupName())
                .build();

    }

    public SubServices toEntity(SubServiceDto subServiceDto) {
        return SubServices.builder()
                .id(subServiceDto.getId())
                .name(subServiceDto.getName())
                .basePrice(subServiceDto.getBasePrice())
                .description(subServiceDto.getDescription())
                .id(subServiceDto.getId())
                .build();
    }
}
