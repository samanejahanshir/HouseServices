package ir.maktab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Integer id;
    private String city;
    private String street;
    @Pattern(regexp = "^[0-9]{10}",message = "length of postal code should be 10")
    private String postalCode;
    private String tag;
}
