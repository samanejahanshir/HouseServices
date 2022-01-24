package ir.maktab.web;

import ir.maktab.data.enums.OrderState;
import ir.maktab.data.model.Address;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.OrderDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.service.OrderService;
import ir.maktab.service.SubServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @RequestMapping("/addNewOrder/{name}")
    public String addNewOrder(@PathVariable("name") String nameService, Model model, HttpSession session) {
/*
        SubServiceDto subServiceDto = service.getSubServiceByName(nameService);
*/
        // OrderDto orderDto = new OrderDto();
        //Object customerDto =(CustomerDto) session.getAttribute("customerDto");
        //  orderDto.setSubServiceDto(subServiceDto);
        session.setAttribute("subService", nameService);
        /*String email = (String) session.getAttribute("email");
         *//*CustomerDto customerDto=new CustomerDto();
        customerDto.setEmail(email);*//*
         *//* orderDto.setCustomerDto(customerDto);
        orderDto.setCustomerDto(customerDto);*/
        model.addAttribute("orderDto", new OrderDto());

        return "RegisterNewOrder";
    }

   /* @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public String addAddressToOrder(@ModelAttribute("orderDto") OrderDto orderDto, @RequestParam("orderDate") String date, Model model, HttpSession session) throws ParseException {
        System.out.println(date);
        //SubServiceDto subServiceDto=SubServiceDto.builder().name(subService).build();
        //orderDto.setSubServiceDto(subServiceDto);
        SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-mm-dd");
        Date dateParse = outSDF.parse(date);
        orderDto.setOrderDoingDate(dateParse);
        session.setAttribute("orderDto", orderDto);
        model.addAttribute("address", new Address());
        return "AddAddress";

    }*/

    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public String saveNewOrder(@ModelAttribute("orderDto") OrderDto orderDto, @RequestParam("orderDate") String date, HttpSession session, Model model) throws ParseException {
        String email = (String) session.getAttribute("email");
        String subService = (String) session.getAttribute("subService");
        SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-mm-dd");
        Date dateParse = outSDF.parse(date);
        orderDto.setOrderDoingDate(dateParse);
        SubServiceDto subServiceDto = SubServiceDto.builder().name(subService).build();
        orderDto.setSubServiceDto(subServiceDto);
        orderService.saveOrder(orderDto, email);
        model.addAttribute("message", "new order added");
        return "CustomerPage";
    }

    @RequestMapping(value = "/allOrdersExpert")
    public String viewAllOrderOfExpert(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        List<OrderDto> orderDtos = orderService.getListOrdersOfSubServiceExpert(email);
        model.addAttribute("listOrder", orderDtos);
        model.addAttribute("typeList", "allOrders");
        return "ViewListOrdersForExpert";
    }

    @RequestMapping(value = "/listWorks")
    public String viewListWorkOfExpert(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        List<OrderDto> orderDtos = orderService.viewListWorkOfExpert(email);
        model.addAttribute("listOrder", orderDtos);
        model.addAttribute("typeList", "workList");
        return "ViewListOrdersForExpert";
    }

    @RequestMapping("/select/{orderId}")
    public String selectWorkByExpert(@PathVariable("orderId") int orderId, Model model) {
        OrderDto orderDto = orderService.getOrderById(orderId);
        model.addAttribute("orderDto", orderDto);
        model.addAttribute("message","");
        return "SelectOrderToWorking";
    }

    @RequestMapping("/startWork/{orderId}")
    public String startWorkByExpert(@PathVariable("orderId") int orderId, Model model) {
        orderService.updateOrderState(orderId, OrderState.STARTED);
        model.addAttribute("message","work started");
        return "redirect:/order/select/"+orderId;
    }

    @ExceptionHandler(RuntimeException.class)
    public final String handleException(RuntimeException ex, Model model, WebRequest request) {
        model.addAttribute("message", ex.getMessage());
        return "errorPage";
    }
}
