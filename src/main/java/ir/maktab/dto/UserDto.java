package ir.maktab.dto;

import ir.maktab.data.enums.UserState;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private UserState state;
    private Date registerDate;
    private double credit;

    public static final class UserDtoBuilder {
        private Integer id;
        private String firstName;
        private String lastName;
        private String email;
        private UserState state;
        private Date registerDate;
        private double credit;

        private UserDtoBuilder() {
        }

        public static UserDtoBuilder anUserDto() {
            return new UserDtoBuilder();
        }

        public UserDtoBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public UserDtoBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDtoBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDtoBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserDtoBuilder withState(UserState state) {
            this.state = state;
            return this;
        }

        public UserDtoBuilder withRegisterDate(Date registerDate) {
            this.registerDate = registerDate;
            return this;
        }

        public UserDtoBuilder withCredit(double credit) {
            this.credit = credit;
            return this;
        }

        public UserDto build() {
            UserDto userDto = new UserDto();
            userDto.lastName = this.lastName;
            userDto.id = this.id;
            userDto.registerDate = this.registerDate;
            userDto.email = this.email;
            userDto.state = this.state;
            userDto.firstName = this.firstName;
            userDto.credit = this.credit;
            return userDto;
        }
    }
}
