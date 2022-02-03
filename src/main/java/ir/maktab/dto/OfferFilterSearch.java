package ir.maktab.dto;

import com.wordnik.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "OfferFilterSearch")
public class OfferFilterSearch {
   private String[] filter=new String[2];
}
