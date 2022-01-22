package ir.maktab.service;

import ir.maktab.data.dao.MainServiceDao;
import ir.maktab.data.model.MainServices;
import ir.maktab.dto.MainServiceDto;
import ir.maktab.dto.mapper.MainServiceMapper;
import ir.maktab.exceptions.MainServiceNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Data
public class MainServicesService {
    private final MainServiceDao mainServiceDao;
    private final MainServiceMapper mainServiceMapper;

    public List<MainServiceDto> getListMainService() {
        List<MainServices> mainServices = mainServiceDao.findAll();
        return mainServices.stream().map(mainServiceMapper::toDto).collect(Collectors.toList());
    }

    public MainServiceDto getByName(String name){
        Optional<MainServices> services = mainServiceDao.findByGroupName(name);
        if (services.isPresent()){
            return mainServiceMapper.toDto(services.get());
        }else {
            throw  new MainServiceNotFoundException();
        }
    }
}
