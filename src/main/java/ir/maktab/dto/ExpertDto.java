package ir.maktab.dto;

import ir.maktab.data.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
