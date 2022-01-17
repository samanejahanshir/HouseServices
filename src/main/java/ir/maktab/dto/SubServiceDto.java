package ir.maktab.dto;


import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class SubServiceDto {
    private Integer id;
    private String groupName;
    private String name;
    private double basePrice;
    private String description;

}
