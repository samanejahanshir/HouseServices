package ir.maktab.controller;

import ir.maktab.data.enums.UserState;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.mapper.UserMapper;
import ir.maktab.service.CustomerService;
import ir.maktab.service.ExpertService;
import ir.maktab.service.ManagerService;
import ir.maktab.service.UserService;
import ir.maktab.service.validation.OnRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    public String expertRegister(@ModelAttribute("expertDto") @Validated(OnRegister.class)ExpertDto expertDto, Model model, @RequestParam("password") String password, HttpSession session) {
        model.addAttribute("expertDto", expertDto);
        String message="";
        model.addAttribute("message",message);
       // expertValidator.validate(expertDto,result);

            try {
                userService.saveExpert(expertDto, password);
                session.setAttribute("email",expertDto.getEmail());
            }catch (RuntimeException e){
                message=e.getMessage();
            }
            return "ExpertPage";

    }

    @RequestMapping(value = "/SignUpCustomer", method = RequestMethod.POST)
    public String customerRegister(@ModelAttribute("customerDto") CustomerDto customerDto, Model model, @RequestParam("password") String password,HttpSession session) {
        // customerDto.setAddresses(Set.of(address));
        userService.saveCustomer(customerDto, password);
        session.setAttribute("email",customerDto.getEmail());
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
    public String doLogin(@PathVariable String name, Model model, @RequestParam("email") String email, @RequestParam("password") String password,HttpSession session) {
        try {
            model.addAttribute("email", email);
            if (name.equals("expert")) {
                if (expertService.findByEmailAndPass(email, password) != null) {
                    session.setAttribute("email",email);
                    return "ExpertPage";
                }
            } else if (name.equals("customer")) {
                CustomerDto customerDto = customerService.findByEmailAndPass(email, password);
                if (customerDto!=null) {
                    if(customerDto.getState().equals(UserState.CONFIRMED)) {
                        session.setAttribute("email", email);
                        return "CustomerPage";
                    }else{
                        model.addAttribute("message","you are not confirm");
                        return "index";
                    }
                }
            } else {
                if (managerService.getManagerByNameAndPass(email, password) != null) {
                    session.setAttribute("email",email);
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
