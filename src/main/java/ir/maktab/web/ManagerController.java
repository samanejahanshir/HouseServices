package ir.maktab.web;

import ir.maktab.config.LastViewInterceptor;
import ir.maktab.dto.ConditionSearch;
import ir.maktab.dto.MainServiceDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.dto.UserDto;
import ir.maktab.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @RequestMapping("/home")
    public String homePageManager(Model model) {
        return "managerPage";
    }

    @RequestMapping("/Signin")
    public String signIn(Model model) {
        model.addAttribute("role_user", "manager");
        return "Login";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String dologin(Model model, @RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
        try {
            if (managerService.getManagerByNameAndPass(email, password) != null) {
                session.setAttribute("emailManager", email);
                return "managerPage";
            }
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "index";

    }

    @RequestMapping("/listUsers")
    public String viewListUsers(Model model, HttpSession session) {
        if (session.getAttribute("emailManager") != null) {
            model.addAttribute("conditionSearch", new ConditionSearch());
            List<UserDto> userDtoList;
            List<UserDto> userDtos = userService.getUserByCondition(new ConditionSearch());
            model.addAttribute("listUserDto", userDtos);
            return "ViewListUsers";
        } else {
            model.addAttribute("message", "you should login");
            return "index";
        }
    }

    @PostMapping("/search")
    public String searchUsers(@ModelAttribute("conditionSearch") ConditionSearch conditionSearch,/*@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate */Model model, HttpSession session) throws ParseException {
        if (session.getAttribute("emailManager") != null) {
          try {
              List<UserDto> userDtoList;
              SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-MM-dd");
          /*  if(!startDate.equals("")) {
               // conditionSearch.setStartDate(outSDF.parse(startDate));
                conditionSearch.setStartDate(startDate);
            }if(!endDate.equals("")) {
               // conditionSearch.setEndDate(outSDF.parse(endDate));
                  conditionSearch.setEndDate(endDate);
              }*/
              if ((conditionSearch.getSubServiceName().equals("") || conditionSearch.getSubServiceName() == null) && conditionSearch.getMaxScore() == 0 && conditionSearch.getMinScore() == 0) {
                  userDtoList = userService.getUserByCondition(conditionSearch);

              } else {
                  userDtoList = userService.getExpertsByCondition(conditionSearch);

              }
              //  session.setAttribute("products", productDtos);
              model.addAttribute("listUserDto", userDtoList);
              //    session.setAttribute("listUserDto",userDtoList);
          }catch (RuntimeException e){
              model.addAttribute("message",e.getMessage());
          }
            return "ViewListUsers";

        } else {
            model.addAttribute("message", "you should login");
            return "index";
        }
    }

    @RequestMapping("/addMainService")
    public String addMainService(Model model, HttpSession session) {
        if (session.getAttribute("emailManager") != null) {
            model.addAttribute("message", "");
            model.addAttribute("mainService", new MainServiceDto());
            return "AddMainServices";
        } else {
            model.addAttribute("message", "you should login");
            return "index";
        }
    }

    @RequestMapping(value = "/saveMainService", method = RequestMethod.POST)
    public String saveMainService(@ModelAttribute("mainService") MainServiceDto mainServiceDto, Model model) {
        try {
            managerService.saveMainServiceToDb(mainServiceDto);
            model.addAttribute("message", "save saccessfully");
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "AddMainServices";
    }

    @RequestMapping("/addSubService")
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
    }

    @RequestMapping(value = "/saveSubService", method = RequestMethod.POST)
    public String saveSubService(@ModelAttribute("subServiceDto") SubServiceDto subServiceDto, Model model) {
        try {
            managerService.saveSubService(subServiceDto);
            model.addAttribute("message", "save saccessfully");
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "AddSubService";
    }

    @RequestMapping("/viewListMainServices")
    public String viewListMainServices(Model model, HttpSession session) {
        if (session.getAttribute("emailManager") != null) {
            List<MainServiceDto> listMainService = mainServices.getListMainService();
            model.addAttribute("role_user", "manager");
            model.addAttribute("listMainServices", listMainService);
            listMainService.forEach(System.out::println);
            return "ViewListMainServiceManager";
        } else {
            model.addAttribute("message", "you should login");
            return "index";
        }
    }

    @RequestMapping("/viewListSubServices/{groupName}")
    public String viewListSubServices(Model model, @PathVariable String groupName, HttpSession session) {
        if (session.getAttribute("emailManager") != null) {
            List<SubServiceDto> listSubService = subServices.getListSubService(groupName);
            model.addAttribute("listSubServices", listSubService);
            if (session.getAttribute("messageSuccess") != null) {
                model.addAttribute("message", session.getAttribute("messageSuccess"));
                session.removeAttribute("messageSuccess");
            }
            if (session.getAttribute("error") != null) {
                model.addAttribute("message", session.getAttribute("error"));
                session.removeAttribute("error");
            }
            model.addAttribute("role_user", "manager");
            return "ViewListSubServiceManager";
        } else {
            model.addAttribute("message", "you should login");
            return "index";
        }
    }

    @RequestMapping("/addExpertToServices/{service}")
    public String addExpertToServices(@PathVariable("service") String service, Model model, HttpSession session) {
        if (session.getAttribute("emailManager") != null) {
            model.addAttribute("service", service);
            return "AddExpertToSubService";
        } else {
            model.addAttribute("message", "you should login");
            return "index";
        }
    }

    @RequestMapping(value = "/saveExpertToServices/{service}", method = RequestMethod.POST)
    public String saveExpertToServices(@PathVariable("service") String service, Model model, @RequestParam("expertEmail") String expertEmail, HttpSession session) {
        String groupName = "";
        try {
            SubServiceDto subServiceByName = subServices.getSubServiceByName(service);
            groupName = subServiceByName.getGroupName();
            expertService.addSubServiceToExpertList(expertEmail, service);
            session.setAttribute("messageSuccess", "expert added to list services");
        } catch (RuntimeException e) {
            session.setAttribute("error", e.getMessage());
        }
        return "redirect:/manager/viewListSubServices/" + groupName;
    }

    @RequestMapping("/viewListNotConfirmUser")
    public String viewListNotConfirmUsers(Model model, HttpSession session) {
        if (session.getAttribute("emailManager") != null) {
            List<UserDto> listUserNoConfirm = managerService.getListUserNoConfirm();
            model.addAttribute("userDtos", listUserNoConfirm);
            if (session.getAttribute("messageSuccess") != null) {
                model.addAttribute("message", session.getAttribute("messageSuccess"));
                session.removeAttribute("messageSuccess");
            }
            return "ViewNotConfirmUser";
        } else {
            model.addAttribute("message", "you should login");
            return "index";
        }
    }

    @RequestMapping("/confirmUser/{id}")
    public String confirmUser(@PathVariable int id, Model model, HttpSession session) {
        if (session.getAttribute("emailManager") != null) {
            managerService.confirmUser(id);
            session.setAttribute("messageSuccess", "confirm is successfully");
            return "redirect:/manager/viewListNotConfirmUser";
        } else {
            model.addAttribute("message", "you should login");
            return "index";
        }
    }

    @RequestMapping("/confirmAll")
    public String confirmAll(Model model, HttpSession session) {
        if (session.getAttribute("emailManager") != null) {
            List<UserDto> listUserNoConfirm = managerService.getListUserNoConfirm();
            managerService.confirmAll(listUserNoConfirm);
            session.setAttribute("messageSuccess", "confirm all successfully");
            return "redirect:/manager/viewListNotConfirmUser";
        } else {
            model.addAttribute("message", "you should login");
            return "index";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("emailManager");
        return "redirect:/index";
    }

    @RequestMapping("/changePass")
    public String changePass(Model model) {
        model.addAttribute("role_user", "manager");
        return "ChangePass";
    }

    @RequestMapping(value = "/saveNewPass", method = RequestMethod.POST)
    public String saveNewPassword(@RequestParam("password") String password, Model model, HttpSession session) {
        String email = (String) session.getAttribute("emailManager");
        managerService.updatePassword(email, password);
        model.addAttribute("message", "change pass is successfuly");
        return "managerPage";
    }

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
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request,Model model) {
//        String referer = request.getHeader("Referer");
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        model.addAttribute("message",ex.getMessage());
        return new ModelAndView(lastView, ex.getBindingResult().getModel());
    }
}
