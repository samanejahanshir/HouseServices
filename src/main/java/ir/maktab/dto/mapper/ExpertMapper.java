package ir.maktab.dto.mapper;

import ir.maktab.data.model.Expert;
import ir.maktab.data.model.User;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class ExpertMapper {
    public ExpertDto toDto(Expert expert) {
        return ExpertDto.builder()
                .id(expert.getId())
                .email(expert.getEmail())
                .firstName(expert.getFirstName())
                .lastName(expert.getLastName())
                .registerDate(expert.getRegisterDate())
                .score(expert.getScore())
                .image(expert.getImage())
                .role(expert.getRole())
                .build();
    }
    public Expert toEntity(ExpertDto expertDto){
        return  Expert.builder()
                .id(expertDto.getId())
                .firstName(expertDto.getFirstName())
                .lastName(expertDto.getLastName())
                .email(expertDto.getEmail())
                .registerDate(expertDto.getRegisterDate())
                .image(expertDto.getImage())
                .role(expertDto.getRole())
                .build();
    }
}
