package ir.maktab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @NotBlank(message = "should not be empty")
    @Size(min = 16,max = 16,message = "length should be 16 number")
    @Pattern(regexp = "^[0-9]{16}",message = "should be number")
    private String number;
    @NotBlank(message = "should not be empty")
    @Size(min = 5,max = 10,message = "length should be 16 number")
    @Pattern(regexp ="^[0-9]{5,10}",message = "should be number" )
    private String password;
    @Min(0)
    @Max(99)
    private int year;
    @Min(value = 1,message = "month should be >1")
    @Max(value = 12,message = "month should be <12")
    private int month;
    @Size(min = 3,max = 4,message = "length should be 3 or 4 number")
    @Pattern(regexp ="^[0-9]{3,4}",message = "should be number" )
    private  String cvv2;
    private  int idOrder;
}
