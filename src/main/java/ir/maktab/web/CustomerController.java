package ir.maktab.web;

import ir.maktab.config.LastViewInterceptor;
import ir.maktab.data.enums.UserState;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.MainServiceDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.service.CustomerService;
import ir.maktab.service.MainServicesService;
import ir.maktab.service.SubServicesService;
import ir.maktab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/customer")
@Controller
@RequiredArgsConstructor
//@SessionAttributes({"email"})
public class CustomerController {
    final CustomerService customerService;
    final SubServicesService subService;
    final UserService userService;
    final MainServicesService mainServices;

    @RequestMapping("/home")
    public String homePageManager(Model model) {
        return "CustomerPage";
    }

    @RequestMapping("/Signup")
    public String signUp(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        return "CustomerRegister";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String customerRegister(@ModelAttribute("customerDto")@Validated CustomerDto customerDto, Model model, HttpSession session) {
        try {
            userService.saveCustomer(customerDto);
            session.setAttribute("email", customerDto.getEmail());
            model.addAttribute("message", "register done successfully,you should waiting for confirm by manager");
        }catch (RuntimeException e){
            model.addAttribute("message", e.getMessage());
            return "CustomerRegister";
        }
        return "index";

    }

    @RequestMapping("/Signin")
    public String signIn(Model model) {
        model.addAttribute("role_user", "customer");
        return "Login";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(Model model, @RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
        CustomerDto customerDto = null;
       try {
           customerDto = customerService.findByEmailAndPass(email, password);
       }catch (RuntimeException e){
           model.addAttribute("message", e.getMessage());
       }
        if (customerDto != null) {
            if (customerDto.getState().equals(UserState.CONFIRMED)) {
                session.setAttribute("email", email);
                return "CustomerPage";
            } else {
                model.addAttribute("message", "you are not confirm");
                return "index";
            }
        } else {
            return "index";
        }
    }

    @RequestMapping("/viewInformation")
    public String viewInformation(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        try {
            CustomerDto customerDto = customerService.getInformation(email);
            model.addAttribute("customerDto", customerDto);
        }catch (RuntimeException e){
            model.addAttribute("message", e.getMessage());
            return "CustomerPage";
        }
        return "CustomerInfo";
    }

    @RequestMapping("/viewListServices")
    public String getListMainService(Model model, HttpSession session) {
        model.addAttribute("role_user", "customer");
        List<MainServiceDto> listMainServices = mainServices.getListMainService();
        model.addAttribute("listMainServices", listMainServices);
        return "ViewListMainServiceManager";
    }

    @RequestMapping("/viewListSubServices/{groupName}")
    public String viewListSubServices(Model model, @PathVariable String groupName) {
        List<SubServiceDto> listSubService = subService.getListSubService(groupName);
        model.addAttribute("listSubServices", listSubService);
        model.addAttribute("role_user", "customer");
        return "ViewListSubServiceManager";
    }
//TODO send email for change password
    @RequestMapping("/changePass")
    public String changePass(Model model) {
        model.addAttribute("role_user", "customer");
        String password = "";
        model.addAttribute("newPass", password);
        return "ChangePass";
    }

    @RequestMapping(value = "/saveNewPass", method = RequestMethod.POST)
    public String saveNewPassword(@RequestParam("password") String password, Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        customerService.updatePassword(email, password);
        model.addAttribute("message", "change pass is successfuly");
        return "CustomerPage";
    }
//TODO pardakht baraye afzayesh credit?
    @RequestMapping("/incrementCredit")
    public String incrementCredit() {
        return "IncrementCredit";
    }

    @RequestMapping(value = "/saveCredit", method = RequestMethod.POST)
    public String saveCredit(Model model, @RequestParam("amount") int amount, HttpSession session) {
        String email = (String) session.getAttribute("email");
        try {
            CustomerDto customerDto = customerService.getCustomerMapper().toDto(customerService.getCustomerByEmail(email));
            customerService.incrementCredit(customerDto, amount);
            model.addAttribute("message", "credit incremented");
        }catch (RuntimeException e){
            model.addAttribute("message", e.getMessage());
        }
        return "IncrementCredit";
    }

    @RequestMapping("/logout")
    public String logOut(HttpSession session) {
        session.removeAttribute("email");
        return "redirect:/index";
    }

   /* @ExceptionHandler(RuntimeException.class)
    public final String handleException(RuntimeException ex, Model model, WebRequest request) {
        model.addAttribute("message", ex.getMessage());
        return "errorPage";
    }*/
   @ExceptionHandler(value = BindException.class)
   public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
//        String referer = request.getHeader("Referer");
       String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
       return new ModelAndView(lastView, ex.getBindingResult().getModel());
   }
}
