package ir.maktab.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "CommendDto")
public class CommendDto {
    private String commend;
    @ApiModelProperty(dataType = "int",value = "score of expert that done this order ")
    private int score;
}
