package ir.maktab.web;

import com.wordnik.swagger.annotations.Api;
import ir.maktab.config.LastViewInterceptor;
import ir.maktab.data.enums.UserState;
import ir.maktab.data.model.VerifyCodeUser;
import ir.maktab.dto.Cart;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.MainServiceDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RequestMapping("/customer")
@Controller
@RequiredArgsConstructor
//@SessionAttributes({"email"})
@Api(tags = "this is customer controller for use home services app")
public class CustomerController {
    final CustomerService customerService;
    final SubServicesService subService;
    final UserService userService;
    final MainServicesService mainServices;
    final VerifyCodeUserService codeUserService;

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
    public String customerRegister(@ModelAttribute("customerDto") @Validated CustomerDto customerDto, Model model, HttpSession session) {
        try {
            userService.saveCustomer(customerDto);
            session.setAttribute("email", customerDto.getEmail());
            model.addAttribute("message", "register done successfully,you should waiting for confirm by manager");
        } catch (RuntimeException e) {
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
        } catch (RuntimeException e) {
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
        } catch (RuntimeException e) {
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
        // String password = "";
        // model.addAttribute("newPass", password);
        return "ChangePass";
    }

    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public String sendEmailForChangePass(@RequestParam("email") String inputEmail, Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (inputEmail.equals(email)) {
            // return "SendEmail";
            int code = sendMail(email);
            if (code != 0) {
                VerifyCodeUser verifyCodeUser = VerifyCodeUser.builder()
                        .code(code)
                        .email(email)
                        .build();
                codeUserService.save(verifyCodeUser);
                model.addAttribute("role_user", "customer");
                model.addAttribute("verifyCode", new VerifyCodeUser());
                return "ChangePassByCode";
            } else {
                return "ChangePass";
            }
        } else {
            model.addAttribute("message", "email invalid!!");
            return "ChangePass";
        }
    }

    @RequestMapping(value = "/checkVerifyCode", method = RequestMethod.POST)
    public String checkVerifyCode(@RequestParam("code") int code, Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        int code1 = codeUserService.getVerifyCodeByEmail(email);
        if (code == code1) {
            model.addAttribute("verify", true);
        } else {
            model.addAttribute("verify", false);
            model.addAttribute("message", "verify code not valid");
        }
        model.addAttribute("role_user", "customer");
        return "ChangePassByCode";

    }

    private int sendMail(String email) {
        int code = 0;
        try {
            code = (int) (Math.random() * (999999 - 100000 + 1) + 1000000);
            String mailText = "verify code is : " + code;
            MailService.sendMail(email, "verifyCode", mailText);
            return code;
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            code = 0;
            return code;
        }
    }

    @RequestMapping(value = "/saveNewPass", method = RequestMethod.POST)
    public String saveNewPassword(Model model, HttpSession session, @RequestParam("password") String password, @RequestParam("re_password") String rePassword) {
        String email = (String) session.getAttribute("email");
            customerService.updatePassword(email, password);
            model.addAttribute("message", "change pass is successfuly");
            codeUserService.deleteVerifyCode(email);
            return "CustomerPage";
    }

    @RequestMapping("/incrementCredit")
    public String incrementCredit(Model model) {
        model.addAttribute("cart", new Cart());
        return "IncrementCredit";
    }

    @RequestMapping(value = "/saveCredit", method = RequestMethod.POST)
    public String saveCredit(@ModelAttribute("cart") @Validated Cart cart, Model model, @RequestParam("amount") int amount, HttpSession session) {
        String email = (String) session.getAttribute("email");
        try {
            CustomerDto customerDto = customerService.getCustomerMapper().toDto(customerService.getCustomerByEmail(email));
            customerService.incrementCredit(customerDto, amount);
            model.addAttribute("message", "credit incremented");
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "CustomerPage";
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
