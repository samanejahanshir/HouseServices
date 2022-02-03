package ir.maktab.dto;

import com.wordnik.swagger.annotations.ApiModel;
import ir.maktab.data.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "ConditionSearch")
public class ConditionSearch {
    private String firstName;
    private String lastName;
    private String email;
    private String subServiceName;
    private int minScore;
    private int maxScore;
    private UserType role;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date endDate;
    private String orderUser;

}
