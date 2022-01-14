package ir.maktab.dto;

import ir.maktab.data.enums.UserState;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Builder
@Data
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private UserState state;
    private Date registerDate;
    private double credit;
}
