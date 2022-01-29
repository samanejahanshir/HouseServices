package ir.maktab.dto.mapper;

import ir.maktab.data.model.Address;
import ir.maktab.data.model.Commend;
import ir.maktab.dto.AddressDto;
import ir.maktab.dto.CommendDto;
import org.springframework.stereotype.Component;

@Component
public class CommendMapper {
    public CommendDto toDto(Commend commend) {
        return CommendDto.builder()
                .commend(commend.getCommend())
                .score(commend.getScore())
                .build();
    }

    public Commend toEntity(CommendDto commendDto) {
        return Commend.builder()
                .commend(commendDto.getCommend())
                .score(commendDto.getScore())
                .build();
    }
}