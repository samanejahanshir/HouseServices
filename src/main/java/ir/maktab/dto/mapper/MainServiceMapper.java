package ir.maktab.dto.mapper;

import ir.maktab.data.model.MainServices;
import ir.maktab.dto.MainServiceDto;
import org.springframework.stereotype.Component;

@Component
public class MainServiceMapper {
    public MainServiceDto toDto(MainServices mainServices) {
        return MainServiceDto.builder()
                .groupName(mainServices.getGroupName())
                .build();
    }

    public MainServices toEntity(MainServiceDto mainServiceDto) {
        return MainServices.builder()
                .groupName(mainServiceDto.getGroupName())
                .build();
    }
}
