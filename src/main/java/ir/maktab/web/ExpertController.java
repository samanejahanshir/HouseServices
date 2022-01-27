package ir.maktab.web;

import ir.maktab.dto.*;
import ir.maktab.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
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

    @RequestMapping("/Signup")
    public String signUp(Model model) {
        model.addAttribute("expertDto", new ExpertDto());
        return "ExpertRegister";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String expertRegister(@ModelAttribute("expertDto") ExpertDto expertDto, Model model, HttpSession session) {
        /*if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error -> model.addAttribute(error.getField(), error.getDefaultMessage()));
            return "ExpertRegister";
        }*/
        userService.saveExpert(expertDto);
        session.setAttribute("email", expertDto.getEmail());
        return "ExpertPage";

    }

    @RequestMapping("/Signin")
    public String signIn(Model model) {
        model.addAttribute("role_user", "expert");
        return "Login";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(Model model, @RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
        model.addAttribute("email", email);
        if (expertService.findByEmailAndPass(email, password) != null) {
            session.setAttribute("email", email);
            return "ExpertPage";
        } else {
            return "index";
        }

    }

    @RequestMapping("/viewInformation")
    public String viewInformation(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        ExpertDto expertDto = expertService.getInformation(email);
        model.addAttribute("expertDto", expertDto);
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
    public String viewListSubServices(Model model, @PathVariable String groupName) {
        List<SubServiceDto> listSubService = subService.getListSubService(groupName);
        model.addAttribute("listSubServices", listSubService);
        model.addAttribute("role_user", "expert");
        return "ViewListSubServiceManager";
    }

    @RequestMapping("/addSubServiceToList/{service}")
    public String saveSubServiceToList(@PathVariable("service") String service, Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        expertService.addSubServiceToExpertList(email, service);
        model.addAttribute("message", "subService added to list");
        return "ExpertPage";
    }

    @RequestMapping("/changePass")
    public String changePass(Model model) {
        model.addAttribute("role_user", "expert");
        String password = "";
        model.addAttribute("newPass", password);
        return "ChangePass";
    }

    @RequestMapping(value = "/saveNewPass", method = RequestMethod.POST)
    public String saveNewPassword(@RequestParam("password") String password, Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        expertService.updatePassword(email, password);
        model.addAttribute("message", "change pass is successfuly");
        return "ExpertPage";
    }

    @RequestMapping("/addOffer/{id}")
    public String addOfferToOrder(@PathVariable("id") int id, Model model) {
        model.addAttribute("offerDto", new OfferDto());
        model.addAttribute("idOrder", id);
        return "AddOfferToOrder";
    }

    @RequestMapping("/saveOffer/{orderId}")
    public String saveOffer(@PathVariable("orderId") int orderId, @ModelAttribute("offerDto") OfferDto offerDto, HttpSession session, Model model) {
        OrderDto orderDto = orderService.getOrderById(orderId);
        offerDto.setOrderDto(orderDto);
        String email = (String) session.getAttribute("email");
        expertService.addOfferToOrder(email, offerDto);
        model.addAttribute("message", "offer added successfuly");
        return "ExpertPage";
    }

    @RequestMapping("/logout")
    public String logOut(HttpSession session) {
        session.removeAttribute("email");
        return "redirect:/index";
    }

    @ExceptionHandler(RuntimeException.class)
    public final String handleException(RuntimeException ex, Model model, WebRequest request) {
        model.addAttribute("message", ex.getMessage());
        return "errorPage";
    }

}
