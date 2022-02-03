package ir.maktab.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import ir.maktab.data.enums.UserState;
import ir.maktab.data.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "UserDto")
public class UserDto {
    private Integer id;
    @ApiModelProperty(dataType = "String",value = "user first name")
    private String firstName;
    @ApiModelProperty(dataType = "String",value = "user last name")
    private String lastName;
    @ApiModelProperty(dataType = "String",value = "user email address")
    private String email;
    @ApiModelProperty(dataType = "Date",value = "register date of user")
    private Date registerDate;
    private UserType role;
    @ApiModelProperty(dataType = "String",value = "user password")
    private String password;
    private UserState state;
    @ApiModelProperty(dataType = "double",value = "user credit")
    private double credit;
}
