package ir.maktab.dto;

import ir.maktab.data.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpertDto {
    private Integer id;
   /* @NotNull(message = "first name can't empty")
    @Pattern(regexp ="^[a-zA-Z]{3}",message = "format first name is invalid !")*/
    private String firstName;
   /* @Pattern(regexp ="^[a-zA-Z]{3}",message = "format last name is invalid !")
    @NotNull(message = "last name can't empty")*/
    private String lastName;
  //  @NotNull(message = "email can't empty")
    private String email;
    private Date registerDate;
    private int score;
   /* @Length(max = 300000,message = "image size is big !")
    @Pattern(regexp = "([^\\s]+(\\.(?i)(jpg))$)",message = "format image should be jpg")
    @NotNull(message = "image can't empty")*/
    private byte[] image;
    private UserType role;

}
