package ir.maktab.dto;

import ir.maktab.data.enums.UserType;
import ir.maktab.service.validation.OnLogin;
import ir.maktab.service.validation.OnRegister;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpertDto {
    private Integer id;
    @NotBlank(message = "should not be empty", groups = {OnLogin.class, OnRegister.class})
    @Pattern(regexp = "^[a-zA-Z]{3,20}", message = "format firstName not valid")
    private String firstName;
    @NotBlank(message = "should not be empty", groups = {OnLogin.class, OnRegister.class})
    @Pattern(regexp = "^[a-zA-Z]{3,20}", message = "format lastName not valid")
    private String lastName;
    @NotBlank(message = "should not be empty", groups = {OnLogin.class, OnRegister.class})
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*" +
            "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "format email not valid")
    private String email;
    private Date registerDate;
    private int score;
    private byte[] image;
    private UserType role;
    private String password;

}
