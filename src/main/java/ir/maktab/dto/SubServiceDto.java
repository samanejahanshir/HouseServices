package ir.maktab.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubServiceDto {
    private Integer id;
    private String groupName;
    private String name;
    private double basePrice;
    private String description;

}
