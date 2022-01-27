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
                .role(user.getRole())
                .password(user.getPassword())
                .state(user.getState())
                .credit(user.getCredit())
                .build();
    }

    public User toEntity(UserDto userDto){
        User user=new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setState(userDto.getState());
        return user;
    }

    public CustomerDto toCustomerDto(UserDto userDto) {
        return CustomerDto.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .state(userDto.getState())
                .credit(userDto.getCredit())
                .build();
    }

    public ExpertDto toExpertDto(UserDto userDto) {
        return ExpertDto.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .state(userDto.getState())
                .credit(userDto.getCredit())
                .build();
    }

    public UserDto expertDtoToUserDto(ExpertDto expertDto){
        return UserDto.builder()
                .id(expertDto.getId())
                .password(expertDto.getPassword())
                .firstName(expertDto.getFirstName())
                .lastName(expertDto.getLastName())
                .role(expertDto.getRole())
                .email(expertDto.getEmail())
                .registerDate(expertDto.getRegisterDate())
                .build();
    }

}
