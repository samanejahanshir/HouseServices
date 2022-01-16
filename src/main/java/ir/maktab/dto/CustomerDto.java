package ir.maktab.dto;

import ir.maktab.data.enums.UserState;
import ir.maktab.data.model.Address;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Address> addresses=new HashSet<>();
}
