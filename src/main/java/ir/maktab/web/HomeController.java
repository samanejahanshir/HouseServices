package ir.maktab.web;

import com.wordnik.swagger.annotations.Api;
import ir.maktab.config.LastViewInterceptor;
import ir.maktab.dto.mapper.UserMapper;
import ir.maktab.service.CustomerService;
import ir.maktab.service.ExpertService;
import ir.maktab.service.ManagerService;
import ir.maktab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SessionAttributes({"role_user", "customerDto", "expertDto"})
@RequiredArgsConstructor
@Api(tags = "this is home controller - home page")
@Controller
public class HomeController {
    final UserService userService;
    final UserMapper userMapper;
    final ManagerService managerService;
    final CustomerService customerService;
    final ExpertService expertService;

    @RequestMapping("/index")
    public String displayHome(HttpSession session, Model model) {

        if(session.getAttribute("error")!=null){
            model.addAttribute("message",session.getAttribute("error"));
            session.removeAttribute("error");
        }
        if(session.getAttribute("messageSuccess")!=null){
            model.addAttribute("message",session.getAttribute("messageSuccess"));
            session.removeAttribute("messageSuccess");
        }
            return "index";
    }

    @RequestMapping("/")
    public String Home() {
        return "index";
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
