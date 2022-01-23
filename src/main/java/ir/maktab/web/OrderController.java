package ir.maktab.web;

import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.OrderDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.service.OrderService;
import ir.maktab.service.SubServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/order")
@Controller
@RequiredArgsConstructor
public class OrderController {
    final OrderService orderService;
    final SubServicesService service;

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

    /////TODO click on subServiceDto on addnew order ......
    @RequestMapping("/addNewOrder/{name}")
    public String addNewOrder(@PathVariable("name") String nameService, Model model, HttpSession session) {
        SubServiceDto subServiceDto = service.getSubServiceByName(nameService);
        // OrderDto orderDto = new OrderDto();
        //Object customerDto =(CustomerDto) session.getAttribute("customerDto");
        //  orderDto.setSubServiceDto(subServiceDto);
        session.setAttribute("subService", nameService);
        String email = (String) session.getAttribute("email");
        /*CustomerDto customerDto=new CustomerDto();
        customerDto.setEmail(email);*/
       /* orderDto.setCustomerDto(customerDto);
        orderDto.setCustomerDto(customerDto);*/
        model.addAttribute("OrderDto", new OrderDto());

        return "RegisterNewOrder";
    }

    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public String saveNewOrder(@ModelAttribute("OrderDto") OrderDto orderDto, HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        String sub = (String) session.getAttribute("subService");
        SubServiceDto subServiceDto = service.getSubServiceByName(sub);
        orderDto.setSubServiceDto(subServiceDto);
        orderService.saveOrder(orderDto, email);
        return "CustomerPage";
    }

    @RequestMapping(value = "/allOrderExpert")
    public String viewAllOrderOfExpert(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        List<OrderDto> orderDtos = orderService.getListOrdersOfSubServiceExpert(email);
        model.addAttribute("listOrder", orderDtos);
        return "ViewListOrdersForExpert";
    }
}
