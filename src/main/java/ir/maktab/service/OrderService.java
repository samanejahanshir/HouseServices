package ir.maktab.service;

import ir.maktab.data.dao.OfferDao;
import ir.maktab.data.dao.OrderDao;
import ir.maktab.data.dao.SubServiceDao;
import ir.maktab.data.enums.OrderState;
import ir.maktab.data.model.Customer;
import ir.maktab.data.model.Expert;
import ir.maktab.data.model.Orders;
import ir.maktab.data.model.SubServices;
import ir.maktab.dto.OrderDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.dto.mapper.OrderMapper;
import ir.maktab.exceptions.ExpertNotExistException;
import ir.maktab.exceptions.SubServiceNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Data
public class OrderService {
    private final OrderDao orderDao;
    private final OrderMapper orderMapper;
    private final SubServiceDao subServiceDao;
    private final CustomerService customerService;
    private final ExpertService expertService;
    private final OfferDao offerDao;

    public void saveOrder(OrderDto orderDto, SubServiceDto subServiceDto, String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        Optional<SubServices> subServicesOptional = subServiceDao.findByName(subServiceDto.getName());
        if (subServicesOptional.isPresent()) {
            Orders orders = orderMapper.toEntity(orderDto);
            orders.setSubServices(subServicesOptional.get());
            orders.setState(OrderState.WAIT_OFFER_EXPERTS);
            orders.setCustomer(customer);
            orderDao.save(orders);
        } else {
            throw new SubServiceNotFoundException();
        }
    }

    public List<OrderDto> getListOrders(String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        List<Orders> orders = orderDao.findByCustomer_Id(customer.getId());
        return orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    public List<OrderDto> getListOrdersThatNotFinished(String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        List<Orders> ordersList = orderDao.getListOrdersThatNotFinished(customer.getId());
        return ordersList.stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    public void selectOfferForOrder(int idExpert, int idOrder) {
        Optional<Orders> orders = orderDao.findById(idOrder);
        if (orders.isPresent()) {
            Orders order = orders.get();
            Optional<Expert> expertOptional = expertService.expertDao.findById(idExpert);
            if (expertOptional.isPresent()) {
                Expert expert = expertOptional.get();
                order.setExpert(expert);
                order.setState(OrderState.WAIT_EXPERT_COME);
                orderDao.save(order);
            } else {
                throw new ExpertNotExistException();
            }
        }
    }

    public void deleteOrder(int orderId) {
        //offerDao.deleteOffersOfAOrder(orderId);
        List<Integer> offersId = offerDao.getListOffers(orderId).stream().map(offer -> offer.getId()).collect(Collectors.toList());
        if (!offersId.isEmpty()) {
            offerDao.deleteByIdIn(offersId);
        }
        orderDao.deleteById(orderId);
    }

    public int RegisterACommentToOrder(int orderId, String comment) {
        return orderDao.updateOrderComment(orderId, comment);
    }

    @Transactional
    public List<OrderDto> getListOrdersOfSubServiceExpert(String email) {
        Expert expert = expertService.getExpertByEmail(email);
        List<String> subServiceNames = expert.getServices().stream().map(subServices -> subServices.getName()).collect(Collectors.toList());
        List<Orders> orders = orderDao.getListOrdersOfSubServiceExpert(subServiceNames);
        return  orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
    }
}
