package ir.maktab.web;

import ir.maktab.dto.mapper.UserMapper;
import ir.maktab.service.CustomerService;
import ir.maktab.service.ExpertService;
import ir.maktab.service.ManagerService;
import ir.maktab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@SessionAttributes({"role_user","customerDto","expertDto"})
@RequiredArgsConstructor
@Controller
public class HomeController {
    final UserService userService;
    final UserMapper userMapper;
    final ManagerService managerService;
    final CustomerService customerService;
    final ExpertService expertService;

    @RequestMapping("/index")
    public String displayHome() {
        return "index";
    }

    @RequestMapping("/")
    public String Home() {
        return "index";
    }

  @ExceptionHandler(RuntimeException.class)
  public final String handleException(RuntimeException ex, Model model, WebRequest request) {
      model.addAttribute("message",ex.getMessage());
      return "errorPage";
  }
}
