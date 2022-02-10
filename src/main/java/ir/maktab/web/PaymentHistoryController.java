package ir.maktab.web;

import ir.maktab.dto.PaymentHistoryDto;
import ir.maktab.service.PaymentHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class PaymentHistoryController {

    final PaymentHistoryService paymentHistoryService;

    @RequestMapping("/customer/paymentHistory")
    public String getPaymentHistory(Model model, HttpSession session){
        if(session.getAttribute("email")!=null){
            String email=(String)session.getAttribute("email");
            List<PaymentHistoryDto> paymentsHistory = paymentHistoryService.getListPaymentsHistory(email);
            model.addAttribute("paymentsHistory",paymentsHistory);
        }
        return "PaymentHistory";
    }
}
