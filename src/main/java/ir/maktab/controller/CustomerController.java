package ir.maktab.controller;

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

import java.util.List;

@RequestMapping("/customer")
@Controller
@RequiredArgsConstructor
public class CustomerController {
    final CustomerService customerService;
    final OrderService orderService;
    final OfferService offerService;

    @RequestMapping("/allOrders/{email}")
    public String displayListOrders(@PathVariable("email") String email, Model model) {
        model.addAttribute("email", email);
        List<OrderDto> orderDtoList = orderService.getListOrders(email);
        model.addAttribute("listOrdersDto", orderDtoList.toArray());
        orderDtoList.forEach(System.out::println);
        return "ViewOrdersCustomer";
    }

    @RequestMapping("/newOrders/{email}")
    public String displayNewOrders(@PathVariable("email") String email, Model model) {
        model.addAttribute("email", email);
        List<OrderDto> orderDtoList = orderService.getListOrdersThatNotFinished(email);
        model.addAttribute("listOrdersDto", orderDtoList.toArray());
        orderDtoList.forEach(System.out::println);
        return "ViewOrdersCustomer";
    }
/////TODO click on subServiceDto on addnew order ......
    @RequestMapping("/addNewOrder/{email}")
    public String addNewOrder(@PathVariable("email") String email,Model model){
        model.addAttribute("email",email);
        model.addAttribute("OrderDto",new OrderDto());
        return "RegisterNewOrder";
    }

}
