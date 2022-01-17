package ir.maktab.dto.mapper;

import ir.maktab.data.model.Offer;
import ir.maktab.data.model.User;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.ExpertDto;
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

    public CustomerDto toCustomerDto(UserDto userDto) {
        return CustomerDto.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
    }

    public ExpertDto toExpertDto(UserDto userDto) {
        return ExpertDto.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
    }

}
