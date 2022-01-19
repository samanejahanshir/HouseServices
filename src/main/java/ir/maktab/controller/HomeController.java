package ir.maktab.controller;

import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.mapper.UserMapper;
import ir.maktab.service.CustomerService;
import ir.maktab.service.ExpertService;
import ir.maktab.service.ManagerService;
import ir.maktab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class HomeController {
    final UserService userService;
    final UserMapper userMapper;
    final ManagerService managerService;
    final CustomerService customerService;
    final ExpertService expertService;

    @RequestMapping("/index")
    public String displayHome() {
        return "index";
    }

    @RequestMapping("/")
    public String Home() {
        return "index";
    }


    @RequestMapping("/Signup/{name}")
    public String signUp(@PathVariable String name, Model model) {
        model.addAttribute("role_user", name);
        if (name.equals("customer")) {
            model.addAttribute("customerDto", new CustomerDto());
            return "CustomerRegister";
        } else {
            model.addAttribute("expertDto", new ExpertDto());
            return "ExpertRegister";
        }
         /*else {
            model.addAttribute("manager", new Manager());
            return "Register";
        }*/
    }


    @RequestMapping(value = "/SignUpExpert", method = RequestMethod.POST)
    public String expertRegister(@Valid @ModelAttribute("expertDto") ExpertDto expertDto, Model model, @RequestParam("password") String password, BindingResult result) {
        model.addAttribute("expertDto", expertDto);
        if (result.hasErrors()) {
            return "ExpertRegister";
        } else {
            userService.saveExpert(expertDto, password);
            return "redirect:index";
        }
    }

    @RequestMapping(value = "/SignUpCustomer", method = RequestMethod.POST)
    public String customerRegister(@ModelAttribute("customerDto") CustomerDto customerDto, Model model, @RequestParam("password") String password) {
        // customerDto.setAddresses(Set.of(address));
        userService.saveCustomer(customerDto, password);
        return "redirect:index";
    }

    /* @RequestMapping(value = "/SignUp", method = RequestMethod.POST)
     public String saveUser(@ModelAttribute("role_user") String role_user, @ModelAttribute("userDto") UserDto userDto, @ModelAttribute("address") Address address, @RequestParam("password") String password,byte[] image, Model model) {
         if (role_user.equals("customer")) {
             CustomerDto customerDto = userMapper.toCustomerDto(userDto);
             // customerDto.setAddresses(Set.of(address));
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
     }*/
    @RequestMapping("/Signin/{name}")
    public String signIn(@PathVariable String name, Model model) {
        model.addAttribute("role_user", name);
        return "Login";
    }

    @RequestMapping("/doLogin/{name}")
    public String doLogin(@PathVariable String name, Model model, @RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            model.addAttribute("email", email);
            if (name.equals("expert")) {
                if (expertService.findByEmailAndPass(email, password) != null) {
                    return "ExpertPage";
                }
            } else if (name.equals("customer")) {
                if (customerService.findByEmailAndPass(email, password) != null) {
                    return "CustomerPage";
                }
            } else {
                if (managerService.getManagerByNameAndPass(email, password) != null) {
                    return "managerPage";
                }
            }
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "Login";

    }

  /*  @RequestMapping("/SignUpManager")
    public String managerRegister(@ModelAttribute("manager") Manager manager) {
        managerService.saveManager(manager);
        return "redirect:index";
    }*/
}
