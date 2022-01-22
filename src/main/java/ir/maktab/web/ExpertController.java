package ir.maktab.web;

import ir.maktab.dto.ExpertDto;
import ir.maktab.service.ExpertService;
import ir.maktab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping("/expert")
@Controller
@RequiredArgsConstructor
//@SessionAttributes({"email"})
public class ExpertController {
    final ExpertService expertService;
    final UserService userService;

    @RequestMapping("/Signup")
    public String signUp(Model model) {
        model.addAttribute("expertDto", new ExpertDto());
        return "ExpertRegister";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String expertRegister(@ModelAttribute("expertDto")ExpertDto expertDto, Model model, HttpSession session) {
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

    @RequestMapping("/dologin")
    public String doLoginBack() {
        return "ExpertPage";
    }
}
