package ir.maktab.web;

import ir.maktab.config.LastViewInterceptor;
import ir.maktab.dto.ConditionSearch;
import ir.maktab.dto.MainServiceDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.dto.UserDto;
import ir.maktab.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    final ManagerService managerService;
    final UserService userService;
    final SubServicesService subServices;
    final MainServicesService mainServices;
    final ExpertService expertService;


    @GetMapping(value = "/listUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> viewListUsers() {
        return userService.getUserByCondition(new ConditionSearch());
    }

    @PostMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> searchUsers(@RequestBody ConditionSearch conditionSearch) throws ParseException {
        List<UserDto> userDtoList = null;
        try {
            SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-MM-dd");
            if ((conditionSearch.getSubServiceName().equals("") || conditionSearch.getSubServiceName() == null) && conditionSearch.getMaxScore() == 0 && conditionSearch.getMinScore() == 0) {
                userDtoList = userService.getUserByCondition(conditionSearch);

            } else {
                userDtoList = userService.getExpertsByCondition(conditionSearch);
            }
        } catch (RuntimeException e) {
            e.getStackTrace();
        }
        return userDtoList;
    }

   /* @RequestMapping("/addMainService")
    public String addMainService(Model model, HttpSession session) {
        if (session.getAttribute("emailManager") != null) {
            model.addAttribute("message", "");
            model.addAttribute("mainService", new MainServiceDto());
            return "AddMainServices";
        } else {
            model.addAttribute("message", "you should login");
            return "index";
        }
    }*/

    @PostMapping(value = "/saveMainService", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveMainService(@RequestBody MainServiceDto mainServiceDto) {
        try {
            managerService.saveMainServiceToDb(mainServiceDto);
        } catch (RuntimeException e) {
            e.getStackTrace();
        }
    }
/*
    @GetMapping(value = "/addSubService",produces = MediaType.APPLICATION_JSON_VALUE)
    public String addSubService(Model model, HttpSession session) {
        //  model.addAttribute("message","");
        if (session.getAttribute("emailManager") != null) {
            model.addAttribute("subServiceDto", new SubServiceDto());
            List<MainServiceDto> mainServiceDtos = mainServices.getListMainService();
            model.addAttribute("MainServiceDtos", mainServiceDtos);
            return "AddSubService";
        } else {
            model.addAttribute("message", "you should login");
            return "index";
        }
    }*/

    @PostMapping(value = "/saveSubService", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveSubService(@RequestBody SubServiceDto subServiceDto) {
        try {
            managerService.saveSubService(subServiceDto);
        } catch (RuntimeException e) {
            e.getStackTrace();
        }
    }

    @GetMapping(value = "/viewListMainServices", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MainServiceDto> viewListMainServices() {
        return mainServices.getListMainService();
    }

    @GetMapping(value = "/viewListSubServices/{groupName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SubServiceDto> viewListSubServices(@PathVariable String groupName) {
        return subServices.getListSubService(groupName);
    }

  /*  @RequestMapping("/addExpertToServices/{service}")
    public String addExpertToServices(@PathVariable("service") String service, Model model, HttpSession session) {
        if (session.getAttribute("emailManager") != null) {
            model.addAttribute("service", service);
            return "AddExpertToSubService";
        } else {
            model.addAttribute("message", "you should login");
            return "index";
        }
    }*/

    @PostMapping(value = "/saveExpertToServices/{service}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveExpertToServices(@PathVariable("service") String service, @RequestBody String expertEmail) {
        try {
            expertService.addSubServiceToExpertList(expertEmail, service);
        } catch (RuntimeException e) {
            e.getStackTrace();
        }
    }

    @GetMapping(value = "/viewListNotConfirmUser",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> viewListNotConfirmUsers() {
           return managerService.getListUserNoConfirm();
    }

    @GetMapping(value = "/confirmUser/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void confirmUser(@PathVariable int id) {
            managerService.confirmUser(id);
    }

    @GetMapping(value = "/confirmAll",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void confirmAll() {
            List<UserDto> listUserNoConfirm = managerService.getListUserNoConfirm();
            managerService.confirmAll(listUserNoConfirm);
    }

   /* @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("emailManager");
        return "redirect:/index";
    }*/
/*
    @RequestMapping("/changePass")
    public String changePass(Model model) {
        model.addAttribute("role_user", "manager");
        return "ChangePass";
    }*/
/*
    @RequestMapping(value = "/saveNewPass", method = RequestMethod.POST)
    public String saveNewPassword(@RequestParam("password") String password, Model model, HttpSession session) {
        String email = (String) session.getAttribute("emailManager");
        managerService.updatePassword(email, password);
        model.addAttribute("message", "change pass is successfuly");
        return "managerPage";
    }*/

  /*  @ExceptionHandler(RuntimeException.class)
    public final String handleException(RuntimeException ex, Model model, WebRequest request) {
        model.addAttribute("message", ex.getMessage());
        return "errorPage";
    }*/

    /* @ExceptionHandler(MainServiceDuplicateException.class)
     public final String handleException(MainServiceDuplicateException ex, Model model, WebRequest request) {
         model.addAttribute("message", ex.getMessage());
         return "AddMainServices";
     }*/
  /* @ExceptionHandler(value = MainServiceDuplicateException.class)
   public ModelAndView loginExceptionHandler(MainServiceDuplicateException ex) {
       Map<String, Object> model = new HashMap<>();
      // model.put("customer", new CustomerDto());
       model.put("message", ex.getMessage());
       return new ModelAndView("AddMainServices", model);
   }*/
    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request, Model model) {
//        String referer = request.getHeader("Referer");
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        model.addAttribute("message", ex.getMessage());
        return new ModelAndView(lastView, ex.getBindingResult().getModel());
    }
}
