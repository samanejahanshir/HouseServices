package ir.maktab.dto;

import ir.maktab.data.enums.UserState;
import ir.maktab.data.enums.UserType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class ExpertDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Date registerDate;
    private int score;
    private byte[] image;
    private UserType role;

}
