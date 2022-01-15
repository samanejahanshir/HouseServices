package ir.maktab.dto;

import ir.maktab.data.enums.UserState;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class CustomerDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Date registerDate;
    private double credit;
    private UserState state;
}
