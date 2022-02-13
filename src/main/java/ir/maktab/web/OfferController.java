package ir.maktab.web;

import com.wordnik.swagger.annotations.Api;
import ir.maktab.config.LastViewInterceptor;
import ir.maktab.data.model.Offer;
import ir.maktab.dto.OfferDto;
import ir.maktab.dto.OfferFilterSearch;
import ir.maktab.dto.OrderDto;
import ir.maktab.service.ExpertService;
import ir.maktab.service.OfferService;
import ir.maktab.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@RequestMapping("/offer")
@Controller
@RequiredArgsConstructor
@Api(tags = "this is offer controller for handle offers of orders")
public class OfferController {
    final OfferService offerService;
    final OrderService orderService;
  //  final ExpertService expertService;

    @RequestMapping("/customer/offer/viewListOffers/{id}")
    public String displayListOffers(@PathVariable int id, Model model, HttpSession session) {
        if (session.getAttribute("email") != null) {
            OrderDto orderDto = orderService.getOrderById(id);
            List<OfferDto> listOffers = offerService.getListOffersForCustomer(orderDto);
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

    @PostMapping("/customer/offer/searchOffers/{id}")
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

    @RequestMapping("/customer/offer/selectOffer/{id}")
    public String selectOffer(@PathVariable int id, Model model, HttpSession session, @ModelAttribute("offerFilter") OfferFilterSearch offerFilter) {
        int orderId = 0;
        try {
            OfferDto offerDto = offerService.findOfferById(id);
            orderService.selectOfferForOrder(offerDto);
            OrderDto orderDto = orderService.getOrderById(offerDto.getOrderDto().getId());
            orderId = orderDto.getId();
            List<OfferDto> listOffers = offerService.getListOffersForCustomer(orderDto);
            model.addAttribute("listOffers", listOffers);
            session.setAttribute("messageSuccess", "select offer successfuly");
        } catch (RuntimeException e) {
            session.setAttribute("error", e.getMessage());
        }
        return "redirect:/customer/offer/viewListOffers/" + orderId;
    }

    @RequestMapping("/expert/offer/viewListOfferExpert")
    public String viewListOffersForExpert(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (session.getAttribute("messageSuccess") != null) {
            model.addAttribute("message", session.getAttribute("messageSuccess"));
            session.removeAttribute("messageSuccess");
        }
        if (session.getAttribute("error") != null) {
            model.addAttribute("message", session.getAttribute("error"));
            session.removeAttribute("error");
        }
        List<OfferDto> offers = offerService.getListOffersForExpert(email);
        model.addAttribute("offers", offers);
        return "ViewListOfferForExpert";
    }

    @RequestMapping("/expert/offer/delete/{offerId}")
    public String deleteOffer(@PathVariable("offerId") int id, Model model, HttpSession session) {
        offerService.getOfferDao().deleteById(id);
        session.setAttribute("messageSuccess", "delete offer successfully");
        return "redirect:/expert/offer/viewListOfferExpert";
    }

    @RequestMapping("/expert/offer/edit/{offerId}")
    public String editOffer(@PathVariable("offerId") int id, Model model, HttpSession session) {
        Optional<Offer> offer = offerService.getOfferDao().findById(id);
        if (offer.isPresent()) {
            OfferDto offerDto = offerService.getOfferMapper().toDto(offer.get());
            model.addAttribute("offerDto", offerDto);
            return "EditOffer";
        } else {
            session.setAttribute("error", "offer not found");
            return "redirect:/expert/offer/viewListOfferExpert";
        }

    }

    @RequestMapping(value = "/expert/offer/updateOffer", method = RequestMethod.POST)
    public String updateOffer(@ModelAttribute("offerDto") @Validated OfferDto offerDto, Model model, HttpSession session) {
        System.out.println(offerDto);
        String email = (String) session.getAttribute("email");
        try {
            offerService.updateOffer(offerDto, email);
        }catch (RuntimeException e){
            session.setAttribute("error", e.getMessage());
        }
        session.setAttribute("messageSuccess", "update offer successfully");
        return "redirect:/expert/offer/viewListOfferExpert";
    }

    @RequestMapping("/expert/offer/addOffer/{id}")
    public String addOfferToOrder(@PathVariable("id") int id, Model model, HttpSession session) {
        model.addAttribute("offerDto", new OfferDto());
        model.addAttribute("idOrder", id);
        if (session.getAttribute("error") != null) {
            model.addAttribute("message", session.getAttribute("error"));
            session.removeAttribute("error");
        }
        if (session.getAttribute("messageSuccess") != null) {
            model.addAttribute("message", session.getAttribute("messageSuccess"));
            session.removeAttribute("messageSuccess");
        }
        return "AddOfferToOrder";
    }

    @RequestMapping("/expert/offer/saveOffer/{orderId}")
    public String saveOffer(@PathVariable("orderId") int orderId, @ModelAttribute("offerDto") OfferDto offerDto, HttpSession session, Model model) {
        try {
            OrderDto orderDto = orderService.getOrderById(orderId);
            offerDto.setOrderDto(orderDto);
            String email = (String) session.getAttribute("email");
            offerService.addOfferToOrder(email, offerDto);
            session.setAttribute("messageSuccess", "offer added successfuly");
        } catch (RuntimeException e) {
            session.setAttribute("error", e.getMessage());
        }
        return "redirect:/expert/offer/addOffer/" + offerDto.getOrderDto().getId();
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
