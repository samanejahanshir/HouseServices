package ir.maktab.controller;

import ir.maktab.dto.MainServiceDto;
import ir.maktab.dto.UserDto;
import ir.maktab.service.ManagerService;
import ir.maktab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/manager")
@Controller
public class ManagerController {
    final ManagerService managerService;
    final UserService userService;

    @RequestMapping("/management")
    public String displayManagementPage() {
        return "";
    }

    @RequestMapping("/listUsers")
    public String viewListUsers(Model model) {
        List<UserDto> listUsers = managerService.getListUsers();
        model.addAttribute("listUsers", listUsers.toArray());
        listUsers.forEach(System.out::println);
        return "ViewListUsers";
    }

    @RequestMapping("/addMainService")
    public String addMainService(Model model) {
        model.addAttribute("message","");
        model.addAttribute("mainService", new MainServiceDto());
        return "AddMainServices";
    }

    @RequestMapping(value = "/saveMainService", method = RequestMethod.POST)
    public String saveMainService(@ModelAttribute("mainService") MainServiceDto mainServiceDto,Model model) {
     boolean error=false;
      try {
          managerService.saveMainServiceToDb(mainServiceDto);
      }catch (RuntimeException e){
          model.addAttribute("message",e.getMessage());
          error=true;
      }
      if(!error){
          model.addAttribute("message","save saccessfully");
      }
      return "AddMainServices";
    }
}
