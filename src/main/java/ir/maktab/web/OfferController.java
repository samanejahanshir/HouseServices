package ir.maktab.web;

import ir.maktab.dto.OfferDto;
import ir.maktab.dto.OrderDto;
import ir.maktab.service.OfferService;
import ir.maktab.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/offer")
@Controller
@RequiredArgsConstructor
public class OfferController {
    final OfferService offerService;
    final OrderService orderService;

    @RequestMapping("/viewListOffers/{id}")
    public String displayListOffers(@PathVariable int id, Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        try {
            OrderDto orderDto = orderService.getOrderById(id);
            List<OfferDto> listOffers = offerService.getListOffers(orderDto);
            model.addAttribute("listOffers", listOffers);
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "ViewListOffersForOrder";
    }

    @RequestMapping("/selectOffer/{id}")
    public String selectOffer(@PathVariable int id, Model model, HttpSession session) {
        try {
            OfferDto offerDto = offerService.findOfferById(id);
            orderService.selectOfferForOrder(offerDto);
            OrderDto orderDto = orderService.getOrderById(offerDto.getOrderDto().getId());
            List<OfferDto> listOffers = offerService.getListOffers(orderDto);
            model.addAttribute("listOffers", listOffers);
            model.addAttribute("message", "select offer successfuly");
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "ViewListOffersForOrder";
    }

}
