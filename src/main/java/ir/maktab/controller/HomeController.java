package ir.maktab.controller;

import ir.maktab.data.model.Address;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.UserDto;
import ir.maktab.dto.mapper.UserMapper;
import ir.maktab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@Controller
public class HomeController {
    final UserService userService;
    final UserMapper userMapper;

    @RequestMapping("/")
    public String displayHome() {
        return "index";
    }


    @RequestMapping("/Signup/{name}")
    public String signUp(@PathVariable String name, Model model) {
       // model.addAttribute("role_user", name);
        if (name.equals("customer")) {
            model.addAttribute("customerDto", new CustomerDto());
            model.addAttribute("address", new Address());
            return "CustomerRegister";
        } else {
            model.addAttribute("expertDto", new ExpertDto());
            return "ExpertRegister";
        }
    }

    @RequestMapping("/Signin/{name}")
    public String signIn(@PathVariable String name, Model model) {
        model.addAttribute("role_user", name);

        return "Login";
    }

    @RequestMapping(value = "/SignUpExpert", method = RequestMethod.POST)
    public String expertRegister(@ModelAttribute("expertDto") ExpertDto expertDto, Model model, @RequestParam("password") String password) {
        System.out.println(expertDto);
        model.addAttribute("expertDto", expertDto);
        userService.saveExpert(expertDto, password);
        return "index";
    }

    @RequestMapping(value = "/SignUpCustomer", method = RequestMethod.POST)
    public String customerRegister(@ModelAttribute("customerDto") CustomerDto customerDto,@ModelAttribute("address")Address address, Model model, @RequestParam("password") String password) {
        customerDto.setAddresses(Set.of(address));
        userService.saveCustomer(customerDto, password);
        return "index";
    }

    @RequestMapping(value = "/SignUp", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("role_user") String role_user, @ModelAttribute("userDto") UserDto userDto, @ModelAttribute("address") Address address, @RequestParam("password") String password, @RequestParam("image") byte[] image, Model model) {
        if (role_user.equals("customer")) {
            CustomerDto customerDto = userMapper.toCustomerDto(userDto);
            customerDto.setAddresses(Set.of(address));
            userService.saveCustomer(customerDto, password);
            model.addAttribute("customerDto", customerDto);
        }
        if (role_user.equals("expert")) {
            ExpertDto expertDto = userMapper.toExpertDto(userDto);
            expertDto.setImage(image);
            System.out.println(expertDto);
            model.addAttribute("expertDto", expertDto);
            userService.saveExpert(expertDto, password);
        }
        model.addAttribute("userDto", role_user);
        return "index";
    }

}
