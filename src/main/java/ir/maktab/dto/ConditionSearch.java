package ir.maktab.dto;

import ir.maktab.data.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConditionSearch {
    private String firstName;
    private String lastName;
    private String email;
    private String subServiceName;
    private int minScore;
    private int maxScore;
    private UserType role;
    private Date startDate;
    private Date endDate;

}
