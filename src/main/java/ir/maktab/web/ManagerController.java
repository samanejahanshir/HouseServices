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

@RequiredArgsConstructor
@Controller
@RequestMapping("/manager")
public class ManagerController {
    final ManagerService managerService;
    final UserService userService;
    final SubServicesService subServices;
    final MainServicesService mainServices;
    final ExpertService expertService;

    @RequestMapping("/Signin")
    public String signIn(Model model) {
        model.addAttribute("role_user", "manager");
        return "Login";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String dologin(Model model, @RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
        if (managerService.getManagerByNameAndPass(email, password) != null) {
            session.setAttribute("email", email);
            return "managerPage";
        } else {
            return "index";
        }
    }

    @RequestMapping("/listUsers")
    public String viewListUsers(Model model) {
        model.addAttribute("conditionSearch", new ConditionSearch());
        return "ViewListUsers";
    }

    @PostMapping("/search")
    public String searchUsers(@ModelAttribute("conditionSearch") ConditionSearch conditionSearch, Model model
            , HttpSession session) {
        List<UserDto> userDtoList;
        if ((conditionSearch.getSubServiceName().equals("") || conditionSearch.getSubServiceName() == null) && conditionSearch.getMaxScore() == 0 && conditionSearch.getMinScore() == 0) {
            userDtoList = userService.getUserByCondition(conditionSearch);

        } else {
            userDtoList = userService.getExpertsByCondition(conditionSearch);

        }
        //  session.setAttribute("products", productDtos);
        model.addAttribute("listUserDto", userDtoList);
        return "ViewListUsers";
    }

    @RequestMapping("/addMainService")
    public String addMainService(Model model) {
        model.addAttribute("message", "");
        model.addAttribute("mainService", new MainServiceDto());
        return "AddMainServices";
    }

    @RequestMapping(value = "/saveMainService", method = RequestMethod.POST)
    public String saveMainService(@ModelAttribute("mainService") MainServiceDto mainServiceDto, Model model) {
        boolean error = false;
        managerService.saveMainServiceToDb(mainServiceDto);
        model.addAttribute("message", "save saccessfully");
        return "AddMainServices";
    }

    @RequestMapping("/addSubService")
    public String addSubService(Model model) {
        //  model.addAttribute("message","");
        model.addAttribute("subServiceDto", new SubServiceDto());
        return "AddSubService";
    }

    @RequestMapping(value = "/saveSubService", method = RequestMethod.POST)
    public String saveSubService(@ModelAttribute("subServiceDto") SubServiceDto subServiceDto, Model model) {
        boolean error = false;
        managerService.saveSubService(subServiceDto);
        model.addAttribute("message", "save saccessfully");
        return "AddSubService";
    }

    @RequestMapping("/viewListMainServices")
    public String viewListMainServices(Model model) {
        List<MainServiceDto> listMainService = mainServices.getListMainService();
        model.addAttribute("role_user", "manager");
        model.addAttribute("listMainServices", listMainService);
        listMainService.forEach(System.out::println);
        return "ViewListMainServiceManager";
    }

    @RequestMapping("/viewListSubServices/{groupName}")
    public String viewListSubServices(Model model, @PathVariable String groupName) {
        List<SubServiceDto> listSubService = subServices.getListSubService(groupName);
        model.addAttribute("listSubServices", listSubService);
        model.addAttribute("role_user", "manager");
        return "ViewListSubServiceManager";
    }

    @RequestMapping("/addExpertToServices/{service}")
    public String addExpertToServices(@PathVariable("service") String service, Model model) {
        model.addAttribute("service", service);
        return "AddExpertToSubService";
    }

    @RequestMapping(value = "/saveExpertToServices/{service}", method = RequestMethod.POST)
    public String saveExpertToServices(@PathVariable("service") String service, Model model, @RequestParam("expertEmail") String expertEmail) {
        expertService.addSubServiceToExpertList(expertEmail, service);
        model.addAttribute("message", "expert added to list services");
        return "managerPage";
    }

    @RequestMapping("/viewListNotConfirmCustomer")
    public String viewListNotConfirmUsers(Model model) {
        List<CustomerDto> customerNoConfirm = managerService.getListCustomerNoConfirm();
        model.addAttribute("listCustomer", customerNoConfirm);
        return "ViewNotConfirmCustomer";
    }

    @RequestMapping("/confirmCustomer/{id}")
    public String confirmCustomer(@PathVariable int id, Model model) {
        CustomerDto customerDto = managerService.getCustomerService().getCustomerById(id);
        managerService.confirmCustomer(customerDto);
        model.addAttribute("message", "confirm is successfully");
        List<CustomerDto> customerNoConfirm = managerService.getListCustomerNoConfirm();
        model.addAttribute("listCustomer", customerNoConfirm);

        return "ViewNotConfirmCustomer";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("email");
        return "redirect:/index";
    }

    @RequestMapping("/changePass")
    public String changePass(Model model) {
        model.addAttribute("role_user", "manager");
        return "ChangePass";
    }

    @RequestMapping(value = "/saveNewPass", method = RequestMethod.POST)
    public String saveNewPassword(@RequestParam("password") String password, Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        managerService.updatePassword(email, password);
        model.addAttribute("message", "change pass is successfuly");
        return "managerPage";
    }

    @ExceptionHandler(RuntimeException.class)
    public final String handleException(RuntimeException ex, Model model, WebRequest request) {
        model.addAttribute("message", ex.getMessage());
        return "errorPage";
    }

}
