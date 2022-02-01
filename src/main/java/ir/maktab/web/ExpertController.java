package ir.maktab.web;

import ir.maktab.config.LastViewInterceptor;
import ir.maktab.data.enums.UserState;
import ir.maktab.data.model.VerifyCodeUser;
import ir.maktab.dto.*;
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

@RequestMapping("/expert")
@Controller
@RequiredArgsConstructor
//@SessionAttributes({"email"})
public class ExpertController {
    final ExpertService expertService;
    final UserService userService;
    final SubServicesService subService;
    final MainServicesService mainServices;
    final OrderService orderService;
    final VerifyCodeUserService codeUserService;
    @RequestMapping("/Signup")
    public String signUp(Model model) {
        model.addAttribute("expertDto", new ExpertDto());
        return "ExpertRegister";
    }

    @RequestMapping("/home")
    public String homePageManager(Model model) {
        return "ExpertPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String expertRegister(@ModelAttribute("expertDto") @Validated ExpertDto expertDto, Model model, HttpSession session) {
        /*if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error -> model.addAttribute(error.getField(), error.getDefaultMessage()));
            return "ExpertRegister";
        }*/
        try {
            userService.saveExpert(expertDto);
            session.setAttribute("email", expertDto.getEmail());
            model.addAttribute("message", "register done successfully,you should waiting for confirm by manager");
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
            return "ExpertRegister";
        }
        return "index";

    }

    @RequestMapping("/Signin")
    public String signIn(Model model) {
        model.addAttribute("role_user", "expert");
        return "Login";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(Model model, @RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
        model.addAttribute("email", email);
        ExpertDto expertDto = null;
        try {
            expertDto = expertService.findByEmailAndPass(email, password);
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
        }
        if (expertDto != null) {
            if (expertDto.getState().equals(UserState.CONFIRMED)) {
                session.setAttribute("email", email);
                return "ExpertPage";
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
            ExpertDto expertDto = expertService.getInformation(email);
            model.addAttribute("expertDto", expertDto);
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
            return "ExpertPage";
        }
        return "ExpertInfo";
    }

    @RequestMapping("/addServiceToList")
    public String addSubServiceToExpertList(Model model) {

        List<MainServiceDto> listMainServices = mainServices.getListMainService();
        model.addAttribute("listMainServices", listMainServices);
        model.addAttribute("role_user", "expert");
        return "ViewListMainServiceManager";
    }

    @RequestMapping("/viewListSubServices/{groupName}")
    public String viewListSubServices(Model model, @PathVariable String groupName, HttpSession session) {
        List<SubServiceDto> listSubService = subService.getListSubService(groupName);
        model.addAttribute("listSubServices", listSubService);
        model.addAttribute("role_user", "expert");
        if (session.getAttribute("messageSuccess") != null) {
            model.addAttribute("message", session.getAttribute("messageSuccess"));
            session.removeAttribute("messageSuccess");
        }
        if (session.getAttribute("error") != null) {
            model.addAttribute("message", session.getAttribute("error"));
            session.removeAttribute("error");
        }
        return "ViewListSubServiceManager";
    }

    @RequestMapping("/addSubServiceToList/{service}")
    public String saveSubServiceToList(@PathVariable("service") String service, Model model, HttpSession session) {
        SubServiceDto subServiceDto = subService.getSubServiceByName(service);
        try {
            String email = (String) session.getAttribute("email");
            expertService.addSubServiceToExpertList(email, service);
            session.setAttribute("messageSuccess", "subService added to list");
        } catch (RuntimeException e) {
            session.setAttribute("error", e.getMessage());
        }
       /* List<SubServiceDto> listSubService = subService.getListSubService(subServiceDto.getGroupName());
        model.addAttribute("listSubServices", listSubService);
        model.addAttribute("role_user", "expert");*/
        return "redirect:/expert/viewListSubServices/" + subServiceDto.getGroupName();
    }

    @RequestMapping("/changePass")
    public String changePass(Model model) {
        model.addAttribute("role_user", "expert");
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
                model.addAttribute("role_user", "expert");
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
        model.addAttribute("role_user", "expert");
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
        if (password.equals(rePassword)) {
            expertService.updatePassword(email, password);
            codeUserService.deleteVerifyCode(email);
            model.addAttribute("message", "change pass is successfuly");
            return "redirect:ExpertPage";
        } else {
            model.addAttribute("verify", true);
            return "ChangePassByCode";
        }
    }


    @RequestMapping("/addOffer/{id}")
    public String addOfferToOrder(@PathVariable("id") int id, Model model, HttpSession session) {
        model.addAttribute("offerDto", new OfferDto());
        model.addAttribute("idOrder", id);
        if (session.getAttribute("error") != null) {
            model.addAttribute("message", session.getAttribute("error"));
            session.removeAttribute("error");
        }
        if (session.getAttribute("messageSuccess") != null) {
            model.addAttribute("message", session.getAttribute("messageSuccess"));
            session.removeAttribute("messageSuccess");
        }
        return "AddOfferToOrder";
    }

    @RequestMapping("/saveOffer/{orderId}")
    public String saveOffer(@PathVariable("orderId") int orderId, @ModelAttribute("offerDto") OfferDto offerDto, HttpSession session, Model model) {
        try {
            OrderDto orderDto = orderService.getOrderById(orderId);
            offerDto.setOrderDto(orderDto);
            String email = (String) session.getAttribute("email");
            expertService.addOfferToOrder(email, offerDto);
            session.setAttribute("messageSuccess", "offer added successfuly");
        } catch (RuntimeException e) {
            session.setAttribute("error", e.getMessage());
        }
        return "redirect:/expert/addOffer/" + offerDto.getOrderDto().getId();
    }

    @RequestMapping("/logout")
    public String logOut(HttpSession session) {
        session.removeAttribute("email");
        return "redirect:/index";
    }

    /*  @ExceptionHandler(RuntimeException.class)
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
