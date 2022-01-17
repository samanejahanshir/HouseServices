package ir.maktab.dto;

import ir.maktab.data.enums.UserState;
import lombok.Builder;
import lombok.Data;
import org.hibernate.usertype.UserType;

import java.util.Date;
@Builder
@Data
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Date registerDate;
    private UserType role;
}
