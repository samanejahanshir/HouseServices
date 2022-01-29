package ir.maktab.web;

import ir.maktab.dto.OfferDto;
import ir.maktab.dto.OfferFilterSearch;
import ir.maktab.dto.OrderDto;
import ir.maktab.service.OfferService;
import ir.maktab.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/offer")
@Controller
@RequiredArgsConstructor
public class OfferController {
    final OfferService offerService;
    final OrderService orderService;

    @RequestMapping("/viewListOffers/{id}")
    public String displayListOffers(@PathVariable int id, Model model, HttpSession session) {
        if (session.getAttribute("email") != null) {
            OrderDto orderDto = orderService.getOrderById(id);
            List<OfferDto> listOffers = offerService.getListOffers(orderDto);
            model.addAttribute("orderId", id);
            model.addAttribute("offerFilter", new OfferFilterSearch());
            model.addAttribute("listOffers", listOffers);
            if (session.getAttribute("messageSuccess") != null) {
                model.addAttribute("message", session.getAttribute("messageSuccess"));
                session.removeAttribute("messageSuccess");
            }
            if (session.getAttribute("error") != null) {
                model.addAttribute("message", session.getAttribute("error"));
                session.removeAttribute("error");
            }
            return "ViewListOffersForOrder";
        } else {
            model.addAttribute("message", "you should login");
            return "index";
        }
    }

    @PostMapping("/searchOffers/{id}")
    public String searchOffers(@PathVariable int id, @ModelAttribute("offerFilter") OfferFilterSearch offerFilter, Model model
            , HttpSession session) {
        model.addAttribute("orderId", id);
        OrderDto orderDto = orderService.getOrderById(id);
        boolean byPrice = false, byScore = false;
        if (Arrays.asList(offerFilter.getFilter()).contains("byPrice")) {
            byPrice = true;
        }
        if (Arrays.asList(offerFilter.getFilter()).contains("byScore")) {
            byScore = true;
        }
        List<OfferDto> listOffers = offerService.getListOffersSortByScoreOrPrice(orderDto, byPrice, byScore);
        model.addAttribute("listOffers", listOffers);
        model.addAttribute("offerFilter", offerFilter);

        return "ViewListOffersForOrder";
    }

    @RequestMapping("/selectOffer/{id}")
    public String selectOffer(@PathVariable int id, Model model, HttpSession session, @ModelAttribute("offerFilter") OfferFilterSearch offerFilter) {
        int orderId = 0;
        try {
            OfferDto offerDto = offerService.findOfferById(id);
            orderService.selectOfferForOrder(offerDto);
            OrderDto orderDto = orderService.getOrderById(offerDto.getOrderDto().getId());
            orderId = orderDto.getId();
            List<OfferDto> listOffers = offerService.getListOffers(orderDto);
            model.addAttribute("listOffers", listOffers);
            session.setAttribute("messageSuccess", "select offer successfuly");
        } catch (RuntimeException e) {
            session.setAttribute("error", e.getMessage());
        }
        return "redirect:/offer/viewListOffers/" + orderId;
    }

    @ExceptionHandler(RuntimeException.class)
    public final String handleException(RuntimeException ex, Model model, WebRequest request) {
        model.addAttribute("message", ex.getMessage());
        return "errorPage";
    }
}
