package ir.maktab.web;

import ir.maktab.data.enums.UserState;
import ir.maktab.dto.*;
import ir.maktab.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/customer")
@Controller
@RequiredArgsConstructor
//@SessionAttributes({"email"})
public class CustomerController {
    final CustomerService customerService;
   // final OrderService orderService;
   // final OfferService offerService;
    final SubServicesService subService;
final UserService userService;
final MainServicesService mainServices;
    @RequestMapping("/Signup")
    public String signUp(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        return "CustomerRegister";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String customerRegister(@ModelAttribute("customerDto") CustomerDto customerDto, Model model,HttpSession session) {
        userService.saveCustomer(customerDto);
        session.setAttribute("customerDto",customerDto);
        return "redirect:/index";
    }

    @RequestMapping("/Signin")
    public String signIn(Model model) {
        model.addAttribute("role_user", "customer");
        return "Login";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(Model model, @RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
        CustomerDto customerDto = customerService.findByEmailAndPass(email, password);
        if (customerDto != null) {
            if (customerDto.getState().equals(UserState.CONFIRMED)) {
                session.setAttribute("email", email);
                return "CustomerPage";
            } else {
                model.addAttribute("message", "you are not confirm");
                return "index";
            }
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/doLogin")
    public String customerPage(Model model, @RequestParam("email") String email, HttpSession session) {

                return "CustomerPage";
    }

//    @RequestMapping("/allOrders")
//    public String displayListOrders(Model model, HttpSession session) {
//        String email = (String) session.getAttribute("email");
//        List<OrderDto> orderDtoList = orderService.getListOrders(email);
//        model.addAttribute("listOrdersDto", orderDtoList);
//        orderDtoList.forEach(System.out::println);
//        return "ViewOrdersCustomer";
//    }

   /* @RequestMapping("/newOrders")
    public String displayNewOrders(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        List<OrderDto> orderDtoList = orderService.getListOrdersThatNotFinished(email);
        model.addAttribute("listOrdersDto", orderDtoList);
        orderDtoList.forEach(System.out::println);
        return "ViewOrdersCustomer";
    }*/

   /* @RequestMapping("/viewListOffers/{id}")
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
    }*/

    @RequestMapping("/viewListServices")
    public String getListMainService(Model model, HttpSession session) {
        model.addAttribute("role_user", "customer");
        List<MainServiceDto> listMainServices = mainServices.getListMainService();
        model.addAttribute("listMainServices",listMainServices);
        return "ViewListMainServiceManager";
    }

    @RequestMapping("/viewListSubServices/{groupName}")
    public String viewListSubServices(Model model, @PathVariable String groupName) {
        List<SubServiceDto> listSubService = subService.getListSubService(groupName);
        model.addAttribute("listSubServices", listSubService);
        model.addAttribute("role_user","customer");
        return "ViewListSubServiceManager";
    }
   /* /////TODO click on subServiceDto on addnew order ......
    @RequestMapping("/addNewOrder")
    public String addNewOrder(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        model.addAttribute("OrderDto", new OrderDto());
        return "RegisterNewOrder";
    }*/

   /* @RequestMapping("/selectOffer/{id}")
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
*/
   @RequestMapping("/changePass")
   public String changePass(Model model) {
       model.addAttribute("role_user", "customer");
       String password = "";
       model.addAttribute("newPass", password);
       return "ChangePass";
   }
   @RequestMapping(value = "/saveNewPass", method = RequestMethod.POST)
   public String saveNewPassword(@RequestParam("password") String password, Model model, HttpSession session) {
       String email = (String) session.getAttribute("email");
       customerService.updatePassword(email, password);
       model.addAttribute("message","change pass is successfuly");
       return "CustomerPage";
   }
}
