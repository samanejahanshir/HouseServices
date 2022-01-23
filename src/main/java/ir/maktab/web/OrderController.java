package ir.maktab.web;

import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.OrderDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.service.OrderService;
import ir.maktab.service.SubServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String addNewOrder(@PathVariable("name")String nameService, Model model, HttpSession session) {
        SubServiceDto subServiceDto = service.getSubServiceByName(nameService);
        OrderDto orderDto = new OrderDto();
        Object customerDto =(CustomerDto) session.getAttribute("customerDto");
        orderDto.setSubServiceDto(subServiceDto);
        //orderDto.setCustomerDto(customerDto);
        String email = (String) session.getAttribute("email");
        model.addAttribute("OrderDto",orderDto);

        return "RegisterNewOrder";
    }
}
