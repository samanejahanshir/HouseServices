package ir.maktab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/Signup")
    public String signUp() {
        return "Register";
    }
    @RequestMapping("/Signin")
    public String signIn() {
        return "Login";
    }
}
