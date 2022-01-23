package ir.maktab.web;

import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.MainServiceDto;
import ir.maktab.dto.OfferDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.service.ExpertService;
import ir.maktab.service.MainServicesService;
import ir.maktab.service.SubServicesService;
import ir.maktab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/Signup")
    public String signUp(Model model) {
        model.addAttribute("expertDto", new ExpertDto());
        return "ExpertRegister";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String expertRegister(@ModelAttribute("expertDto") ExpertDto expertDto, Model model, HttpSession session) {
        try {
            userService.saveExpert(expertDto);
            session.setAttribute("expertDto", expertDto);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
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
        model.addAttribute("message","change pass is successfuly");
        return "ExpertPage";
    }

    /*@RequestMapping("/addOffer/{id}")
    public String addOfferToOrder(@PathVariable("id")int id, Model model){
        model.addAttribute("offerDto",new OfferDto());

    }*/
}
