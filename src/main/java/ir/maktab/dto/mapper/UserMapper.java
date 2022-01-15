package ir.maktab.dto.mapper;

import ir.maktab.data.model.Offer;
import ir.maktab.data.model.User;
import ir.maktab.dto.OfferDto;
import ir.maktab.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .registerDate(user.getRegisterDate())
                .build();
    }

}
