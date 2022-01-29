package ir.maktab.web;

import ir.maktab.data.model.Commend;
import ir.maktab.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@RequestMapping("/commend")
@Controller
@RequiredArgsConstructor
public class CommendController {
    final OrderService orderService;

    @RequestMapping("/addCommend/{idOrder}")
    public String addCommendToOrder(@PathVariable("idOrder") int id, Model model, HttpSession session) {
        session.setAttribute("idOrder", id);
        model.addAttribute("commend", new Commend());
        if(session.getAttribute("messageSuccess")!=null){
            model.addAttribute("message",session.getAttribute("messageSuccess"));
            session.removeAttribute("messageSuccess");
        }
        if(session.getAttribute("error")!=null){
            model.addAttribute("message",session.getAttribute("error"));
            session.removeAttribute("error");
        }
        return "AddCommendToOrder";
    }

    @RequestMapping(value = "/saveCommend", method = RequestMethod.POST)
    public String saveCommend(@ModelAttribute("commend") Commend commend, Model model, HttpSession session) {
        int id = (Integer) session.getAttribute("idOrder");
        try {
            orderService.registerACommentToOrder(commend, id);
            session.setAttribute("messageSuccess", "commend added successfully");
        }catch (RuntimeException e){
            session.setAttribute("error",e.getMessage());
        }
        return "redirect:/commend/addCommend/"+id;
    }
}
