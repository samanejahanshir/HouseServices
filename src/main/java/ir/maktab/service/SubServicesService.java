package ir.maktab.service;

import ir.maktab.data.dao.SubServiceDao;
import ir.maktab.data.model.SubServices;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.dto.mapper.SubServiceMapper;
import ir.maktab.exceptions.SubServiceNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Data
public class SubServicesService {
    private final SubServiceDao subServices;
    private final SubServiceMapper subServiceMapper;

    public List<SubServiceDto> getListSubService(String groupName) {
        List<SubServices> subServices = this.subServices.findAllByMainServices_GroupName(groupName);
        return subServices.stream().map(subServiceMapper::toDto).collect(Collectors.toList());
    }

    public SubServiceDto getSubServiceByName(String name) {
        Optional<SubServices> services = subServices.findByName(name);
        if (services.isPresent()) {
            return subServiceMapper.toDto(services.get());
        } else {
            throw new SubServiceNotFoundException();
        }
    }
}
