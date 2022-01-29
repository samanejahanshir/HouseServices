package ir.maktab.web;

import ir.maktab.config.LastViewInterceptor;
import ir.maktab.data.enums.OrderState;
import ir.maktab.dto.MainServiceDto;
import ir.maktab.dto.OrderDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.service.MainServicesService;
import ir.maktab.service.OrderService;
import ir.maktab.service.SubServicesService;
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
    final SubServicesService subServices;
    final MainServicesService mainServices;

    @RequestMapping("/allOrders")
    public String displayListOrders(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        List<OrderDto> orderDtoList = orderService.getListOrders(email);
        model.addAttribute("listOrdersDto", orderDtoList);
        return "ViewOrdersCustomer";
    }

    @RequestMapping("/newOrders")
    public String displayNewOrders(Model model, HttpSession session) {
        if(session.getAttribute("email")!=null) {
            String email = (String) session.getAttribute("email");
            List<OrderDto> orderDtoList = orderService.getListOrdersThatNotFinished(email);
            model.addAttribute("listOrdersDto", orderDtoList);

            if (session.getAttribute("messageSuccess") != null) {
                String message = (String) session.getAttribute("messageSuccess");
                model.addAttribute("message", message);
                session.removeAttribute("messageSuccess");
            }
            return "ViewOrdersCustomer";
        }else {
            model.addAttribute("message", "you should login");
            return "index";
        }
    }

    @RequestMapping("/addNewOrder/{name}")
    public String addNewOrder(@PathVariable("name") String nameService, Model model, HttpSession session) {
        if(session.getAttribute("email")!=null) {
            session.setAttribute("subService", nameService);
            model.addAttribute("orderDto", new OrderDto());
            return "RegisterNewOrder";
        }else {
            model.addAttribute("message", "you should login");
            return "index";
        }
    }

    @RequestMapping("/addNewOrder")
    public String addNewOrderCustomer( Model model, HttpSession session) {
       if(session.getAttribute("email")!=null) {
           model.addAttribute("orderDto", new OrderDto());
           List<MainServiceDto> mainServiceDtos = mainServices.getListMainService();
           model.addAttribute("MainServiceDtos", mainServiceDtos);
           return "AddNewOrder";
       }else {
           model.addAttribute("message", "you should login");
           return "index";
       }
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public  String searchSubServices(@ModelAttribute("orderDto") OrderDto orderDto,Model model,HttpSession session){
        List<SubServiceDto> listSubService = subServices.getListSubService(orderDto.getSubServiceDto().getGroupName());
        model.addAttribute("subServiceDtoList",listSubService);
        if(!listSubService.isEmpty()) {
            model.addAttribute("select", true);
        }
        return "AddNewOrder";
    }

    @RequestMapping(value = "/saveNewOrder", method = RequestMethod.POST)
    public String saveNewOrder(@ModelAttribute("orderDto") @Validated OrderDto orderDto, @RequestParam("orderDate") String date, HttpSession session, Model model) throws ParseException {
        String email = (String) session.getAttribute("email");
        SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-mm-dd");
        Date dateParse = outSDF.parse(date);
        orderDto.setOrderDoingDate(dateParse);
        orderService.saveOrder(orderDto, email);
        model.addAttribute("message", "new order added");
        return "CustomerPage";
    }

    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public String saveNewOrderFromServiceInSession(@ModelAttribute("orderDto") @Validated OrderDto orderDto, @RequestParam("orderDate") String date, HttpSession session, Model model) throws ParseException {
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
        if(session.getAttribute("email")!=null) {
            String email = (String) session.getAttribute("email");
            List<OrderDto> orderDtos = orderService.getListOrdersOfSubServiceExpert(email);
            model.addAttribute("listOrder", orderDtos);
            model.addAttribute("typeList", "allOrders");
            return "ViewListOrdersForExpert";
        }else {
            model.addAttribute("message", "you should login");
            return "index";
        }
    }

    @RequestMapping(value = "/listWorks")
    public String viewListWorkOfExpert(Model model, HttpSession session) {
        if(session.getAttribute("email")!=null) {
            String email = (String) session.getAttribute("email");
            List<OrderDto> orderDtos = orderService.viewListWorkOfExpert(email);
            model.addAttribute("listOrder", orderDtos);
            model.addAttribute("typeList", "workList");
            return "ViewListOrdersForExpert";
        }else {
            model.addAttribute("message", "you should login");
            return "index";
        }
    }

    @RequestMapping("/select/{orderId}")
    public String selectWorkByExpert(@PathVariable("orderId") int orderId, Model model, HttpSession session) {
       if(session.getAttribute("email")!=null) {
           OrderDto orderDto = orderService.getOrderById(orderId);
           String message = "";
           if (session.getAttribute("messageSuccess") != null) {
               message = (String) session.getAttribute("messageSuccess");
               session.removeAttribute("messageSuccess");
           }
           model.addAttribute("orderDto", orderDto);
           model.addAttribute("message", message);
           return "SelectOrderToWorking";
       }else {
           model.addAttribute("message", "you should login");
           return "index";
       }
    }

    @RequestMapping("/startWork/{orderId}")
    public String startWorkByExpert(@PathVariable("orderId") int orderId, Model model, HttpSession session) {
       if(session.getAttribute("email")!=null) {
           orderService.updateOrderState(orderId, OrderState.STARTED);
           session.setAttribute("messageSuccess", "work started");
           return "redirect:/order/select/" + orderId;
       }else {
           model.addAttribute("message", "you should login");
           return "index";
       }
    }

    @RequestMapping("/endWork/{orderId}")
    public String endWorkByExpert(@PathVariable("orderId") int orderId, Model model, HttpSession session) {
       if(session.getAttribute("email")!=null) {
           orderService.updateOrderState(orderId, OrderState.DONE);
           session.setAttribute("messageSuccess", "work done.");
           return "redirect:/order/select/" + orderId;
       }else {
           model.addAttribute("message", "you should login");
           return "index";
       }
    }

    @RequestMapping("/payByCredit/{orderId}")
    public String paymentForEndingWork(@PathVariable("orderId") int orderId, Model model, HttpSession session) {
       if(session.getAttribute("email")!=null) {
           orderService.updateOrderStateToPaid(orderId);
           session.setAttribute("messageSuccess", "The payment was success.");
           return "redirect:/order/newOrders";
       }else {
           model.addAttribute("message", "you should login");
           return "index";
       }
    }

    @RequestMapping("/showScore/{orderId}")
    public String showScoreOrderForExpert(@PathVariable("orderId") int orderId,Model model, HttpSession session) {
      if(session.getAttribute("email")!=null) {
          try {
              int score = orderService.getScoreOrderForExpert(orderId);
              session.setAttribute("scoreExpert", Integer.toString(score));
          } catch (RuntimeException e) {
              session.setAttribute("error", e.getMessage());
          }
          return "redirect:/order/historyWorks";
      }else {
          model.addAttribute("message", "you should login");
          return "index";
      }
    }

   /* @ExceptionHandler(RuntimeException.class)
    public final String handleException(RuntimeException ex, Model model, WebRequest request) {
        model.addAttribute("message", ex.getMessage());
        return "errorPage";
    }*/

    @RequestMapping("/historyWorks")
    public String showHistoryWorks(Model model, HttpSession session) {
        if(session.getAttribute("email")!=null) {
            String email = (String) session.getAttribute("email");
            List<OrderDto> orderDtoList = orderService.getHistoryWorksOfExpert(email);
            model.addAttribute("typeList", "historyList");
            model.addAttribute("listOrder", orderDtoList);
            String score = "";
            if (session.getAttribute("scoreExpert") != null) {
                score = (String) session.getAttribute("scoreExpert");
                session.removeAttribute("scoreExpert");
            }
            if (session.getAttribute("error") != null) {
                model.addAttribute("message", session.getAttribute("error"));
                session.removeAttribute("error");
            }
            model.addAttribute("score", score);
            return "ViewListOrdersForExpert";
        }else {
            model.addAttribute("message", "you should login");
            return "index";
        }
    }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
//        String referer = request.getHeader("Referer");
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getBindingResult().getModel());
    }
}
