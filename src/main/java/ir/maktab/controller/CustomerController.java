package ir.maktab.controller;

import ir.maktab.dto.OfferDto;
import ir.maktab.dto.OrderDto;
import ir.maktab.dto.UserDto;
import ir.maktab.service.CustomerService;
import ir.maktab.service.OfferService;
import ir.maktab.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/customer")
@Controller
@RequiredArgsConstructor
public class CustomerController {
    final CustomerService customerService;
    final OrderService orderService;
    final OfferService offerService;

    @RequestMapping("/allOrders")
    public String displayListOrders(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        List<OrderDto> orderDtoList = orderService.getListOrders(email);
        model.addAttribute("listOrdersDto", orderDtoList);
        orderDtoList.forEach(System.out::println);
        return "ViewOrdersCustomer";
    }

    @RequestMapping("/newOrders")
    public String displayNewOrders(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        List<OrderDto> orderDtoList = orderService.getListOrdersThatNotFinished(email);
        model.addAttribute("listOrdersDto", orderDtoList);
        orderDtoList.forEach(System.out::println);
        return "ViewOrdersCustomer";
    }

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

    /////TODO click on subServiceDto on addnew order ......
    @RequestMapping("/addNewOrder")
    public String addNewOrder(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        model.addAttribute("OrderDto", new OrderDto());
        return "RegisterNewOrder";
    }

    @RequestMapping("/selectOffer/{id}")
    public String selectOffer(@PathVariable int id, Model model, HttpSession session) {
        try {
            OfferDto offerDto = offerService.findOfferById(id);
            orderService.selectOfferForOrder(offerDto);
            OrderDto orderDto = orderService.getOrderById(offerDto.getOrderDto().getId());
            List<OfferDto> listOffers = offerService.getListOffers(orderDto);
            model.addAttribute("listOffers", listOffers);
            model.addAttribute("message","select offer successfuly");
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "ViewListOffersForOrder";
    }

}
