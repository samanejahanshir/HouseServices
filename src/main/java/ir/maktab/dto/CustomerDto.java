package ir.maktab.dto;

import ir.maktab.data.enums.UserState;
import ir.maktab.data.enums.UserType;
import ir.maktab.data.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Integer id;
  /*  @NotNull(message = "first name can't empty")
    @Pattern(regexp ="^[a-zA-Z]{3}",message = "format first name is invalid !")*/
    private String firstName;
   /* @NotNull(message = "last name can't empty")
    @Pattern(regexp ="^[a-zA-Z]{3}",message = "format first name is invalid !")*/
    private String lastName;
  //  @NotNull(message = "email can't empty")
    private String email;
    private Date registerDate;
    private double credit;
    private UserState state;
   // private Set<Address> addresses=new HashSet<>();
    private UserType role;

}
