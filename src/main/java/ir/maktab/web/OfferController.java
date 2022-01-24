package ir.maktab.web;

import ir.maktab.dto.*;
import ir.maktab.service.OfferService;
import ir.maktab.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

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
       /* String email = (String) session.getAttribute("email");
            OrderDto orderDto = orderService.getOrderById(id);
            List<OfferDto> listOffers = offerService.getListOffers(orderDto);*/
            model.addAttribute("orderId",id);
            model.addAttribute("offerFilter",new OfferFilterSearch());
        return "ViewListOffersForOrder";
    }
//TODO  باید هر دو انتخاب شوند وگرنه ارور میدهد
    @PostMapping("/searchOffers/{id}")
    public String searchOffers(@PathVariable int id,@ModelAttribute("offerFilter") OfferFilterSearch offerFilter, Model model
            , HttpSession session) {
        OrderDto orderDto = orderService.getOrderById(id);
        boolean byPrice=false,byScore=false;
        if(offerFilter.getByPrice().equals("byPrice")){
            byPrice=true;
        }
        if(offerFilter.getByScore().equals("byScore")){
            byScore=true;
        }
        List<OfferDto> listOffers = offerService.getListOffersSortByScoreOrPrice(orderDto,byPrice,byScore);
        model.addAttribute("listOffers", listOffers);
        return "ViewListOffersForOrder";
    }

    @RequestMapping("/selectOffer/{id}")
    public String selectOffer(@PathVariable int id, Model model, HttpSession session) {
            OfferDto offerDto = offerService.findOfferById(id);
            orderService.selectOfferForOrder(offerDto);
            OrderDto orderDto = orderService.getOrderById(offerDto.getOrderDto().getId());
            List<OfferDto> listOffers = offerService.getListOffers(orderDto);
            model.addAttribute("listOffers", listOffers);
            model.addAttribute("message", "select offer successfuly");
        return "ViewListOffersForOrder";
    }

    @ExceptionHandler(RuntimeException.class)
    public final String handleException(RuntimeException ex,Model model, WebRequest request) {
        model.addAttribute("message",ex.getMessage());
        return "errorPage";
    }
}
