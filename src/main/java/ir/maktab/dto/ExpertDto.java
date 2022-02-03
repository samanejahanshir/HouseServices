package ir.maktab.dto;

import com.wordnik.swagger.annotations.ApiModel;
import ir.maktab.data.enums.UserState;
import ir.maktab.data.enums.UserType;
import ir.maktab.service.validation.OnLogin;
import ir.maktab.service.validation.OnRegister;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "ExpertDto")
public class ExpertDto {
    private Integer id;
    @NotBlank(message = "should not be empty")
    @Length(min = 3,message = "Length should be bigger than 3 character")
    @Pattern(regexp = "^[a-zA-Z]{3,30}", message = "firstName only contains letters")
    private String firstName;
    @NotBlank(message = "should not be empty")
    @Length(min = 3,message = "Length should be bigger than 3 character")
    @Pattern(regexp = "^[a-zA-Z]{3,30}", message = "lastName only contains letters")
    private String lastName;
    @NotBlank(message = "should not be empty")
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*" +
            "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "format email not valid")
    private String email;
    private Date registerDate;
    private int score;
   // @Pattern(regexp= "([^\\\\s]+(\\\\.(?i)(jpg))$)",message = "format image should be jpg")
   @NotNull(message = "image can't empty")
   @NotEmpty(message = "image can't empty")
    private byte[] image;
    private UserType role;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",message = "password length should >8 and contain a-z and A-z")
    private String password;
    private List<SubServiceDto> subServiceDto=new ArrayList<>();
    private UserState state;
    private double credit;

}
