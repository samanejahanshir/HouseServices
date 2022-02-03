package ir.maktab.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "MainServiceDto")
public class MainServiceDto {
    @ApiModelProperty(dataType = "String",value = "name of main service ")
    private String groupName;

}
