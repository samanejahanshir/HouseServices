package ir.maktab.dto.mapper;

import ir.maktab.data.model.Address;
import ir.maktab.data.model.Customer;
import ir.maktab.dto.AddressDto;
import ir.maktab.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public AddressDto toDto(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .street(address.getStreet())
                .tag(address.getTag())
                .build();
    }
    public Address toEntity(AddressDto addressDto){
        return  Address.builder()
                .city(addressDto.getCity())
                .postalCode(addressDto.getPostalCode())
                .tag(addressDto.getTag())
                .street(addressDto.getStreet())
                .build();
    }
}
