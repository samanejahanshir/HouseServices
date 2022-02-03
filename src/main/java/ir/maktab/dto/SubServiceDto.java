package ir.maktab.dto;


import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "SubServiceDto")
public class SubServiceDto {
    private Integer id;
    @ApiModelProperty(dataType = "String",value = "Main service name")
    private String groupName;
    @ApiModelProperty(dataType = "String",value = "subService  name")
    private String name;
    @ApiModelProperty(dataType = "double",value = "base price for doing subService")
    private double basePrice;
    @ApiModelProperty(dataType = "String",value = "description for sub service")
    private String description;

}
