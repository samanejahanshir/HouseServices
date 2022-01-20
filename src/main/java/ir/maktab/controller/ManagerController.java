package ir.maktab.controller;

import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.MainServiceDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.dto.UserDto;
import ir.maktab.service.MainServicesService;
import ir.maktab.service.ManagerService;
import ir.maktab.service.SubServicesService;
import ir.maktab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ManagerController {
    final ManagerService managerService;
    final UserService userService;
    final SubServicesService subServices;
    final MainServicesService mainServices;

    @RequestMapping("/management")
    public String displayManagementPage() {
        return "";
    }

    @RequestMapping("manager/listUsers")
    public String viewListUsers(Model model) {
        List<UserDto> listUsers = managerService.getListUsers();
        listUsers.forEach(System.out::println);
        model.addAttribute("listUsers", listUsers);
        return "ViewListUsers";
    }

    @RequestMapping("manager/addMainService")
    public String addMainService(Model model) {
        model.addAttribute("message", "");
        model.addAttribute("mainService", new MainServiceDto());
        return "AddMainServices";
    }

    @RequestMapping(value = "manager/saveMainService", method = RequestMethod.POST)
    public String saveMainService(@ModelAttribute("mainService") MainServiceDto mainServiceDto, Model model) {
        boolean error = false;
        try {
            managerService.saveMainServiceToDb(mainServiceDto);
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
            error = true;
        }
        if (!error) {
            model.addAttribute("message", "save saccessfully");
        }
        return "AddMainServices";
    }

    @RequestMapping("manager/addSubService")
    public String addSubService(Model model) {
        //  model.addAttribute("message","");
        model.addAttribute("subServiceDto", new SubServiceDto());
        return "AddSubService";
    }

    @RequestMapping(value = "manager/saveSubService", method = RequestMethod.POST)
    public String saveSubService(@ModelAttribute("subServiceDto") SubServiceDto subServiceDto, Model model) {
        boolean error = false;
        try {
            managerService.saveSubService(subServiceDto);
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
            error = true;
        }
        if (!error) {
            model.addAttribute("message", "save saccessfully");
        }
        return "AddSubService";
    }

    @RequestMapping("manager/viewListMainServices")
    public String viewListMainServices(Model model) {
        List<MainServiceDto> listMainService = mainServices.getListMainService();
        model.addAttribute("listMainServices", listMainService);
        listMainService.forEach(System.out::println);
        return "ViewListMainServiceManager";
    }

    @RequestMapping("manager/viewListSubServices/{groupName}")
    public String viewListSubServices(Model model, @PathVariable String groupName) {
        List<SubServiceDto> listSubService = subServices.getListSubService(groupName);
        model.addAttribute("listSubServices", listSubService);
        return "ViewListSubServiceManager";
    }

    @RequestMapping("/manager/viewListNotConfirmCustomer")
    public String viewListNotConfirmUsers(Model model) {
        List<CustomerDto> customerNoConfirm = managerService.getListCustomerNoConfirm();
        model.addAttribute("listCustomer", customerNoConfirm);
        return "ViewNotConfirmCustomer";
    }

    @RequestMapping("/manager/confirmCustomer/{id}")
    public String confirmCustomer(@PathVariable int id, Model model) {
        try {
            CustomerDto customerDto = managerService.getCustomerService().getCustomerById(id);
            managerService.confirmCustomer(customerDto);
            model.addAttribute("message", "confirm is successfully");
            List<CustomerDto> customerNoConfirm = managerService.getListCustomerNoConfirm();
            model.addAttribute("listCustomer", customerNoConfirm);
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "ViewNotConfirmCustomer";
    }

    //ToDo   parameter email not found
    @RequestMapping("/manager/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("email");
        return "redirect:index";
    }
}
