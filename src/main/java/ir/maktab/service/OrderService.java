package ir.maktab.service;

import ir.maktab.data.dao.OfferDao;
import ir.maktab.data.dao.OrderDao;
import ir.maktab.data.dao.SubServiceDao;
import ir.maktab.data.enums.OfferState;
import ir.maktab.data.enums.OrderState;
import ir.maktab.data.model.*;
import ir.maktab.dto.OfferDto;
import ir.maktab.dto.OrderDto;
import ir.maktab.dto.mapper.CustomerMapper;
import ir.maktab.dto.mapper.OrderMapper;
import ir.maktab.exceptions.CreditNotEnoughException;
import ir.maktab.exceptions.ExpertNotExistException;
import ir.maktab.exceptions.OrderNotFoundException;
import ir.maktab.exceptions.SubServiceNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Data
public class OrderService {
    final OrderDao orderDao;
    final OrderMapper orderMapper;
    final SubServiceDao subServiceDao;
    final CustomerService customerService;
    final ExpertService expertService;
    final OfferDao offerDao;
    final CustomerMapper customerMapper;
    final CommendService commendService;


    public void saveOrder(OrderDto orderDto, String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        Optional<SubServices> subServicesOptional = subServiceDao.findByName(orderDto.getSubServiceDto().getName());
        if (subServicesOptional.isPresent() && customer != null) {
            orderDto.setCustomerDto(customerMapper.toDto(customer));
            Orders orders = orderMapper.toEntity(orderDto);
            orders.setUuid(UUID.randomUUID().toString());
            orders.setState(OrderState.WAIT_OFFER_EXPERTS);
            orders.setSubServices(subServicesOptional.get());
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

    @Transactional
    public void selectOfferForOrder(OfferDto offerDto) {
        Optional<Orders> orders = orderDao.findById(offerDto.getOrderDto().getId());
        if (orders.isPresent()) {
            Orders order = orders.get();
            Expert expert1 = expertService.getExpertByEmail(offerDto.getExpertDto().getEmail());
            if (expert1 != null) {
                order.setExpert(expert1);
                order.setState(OrderState.WAIT_EXPERT_COME);
                order.setProposedPrice(offerDto.getOfferPrice());
                orderDao.save(order);
                setStateOfferToReject(offerDto, order);
            } else {
                throw new ExpertNotExistException();
            }
        } else {
            throw new OrderNotFoundException();
        }
    }

    public void setStateOfferToReject(OfferDto offerDto, Orders order) {
        Optional<Offer> offerOptional = offerDao.findById(offerDto.getId());
        if (offerOptional.isPresent()) {
            Offer offer = offerOptional.get();
            offer.setState(OfferState.ACCEPT);
            offerDao.save(offer);
            List<Offer> offerListReject = offerDao.getListOfferThatNotSelected(OfferState.NEW, order.getId());
            offerListReject.forEach(offer1 -> offer1.setState(OfferState.REJECT));
            offerDao.saveAll(offerListReject);
        }
    }

    public void deleteOrder(int orderId) {
        //offerDao.deleteOffersOfAOrder(orderId);
        List<Integer> offersId = offerDao.findByOrders_Id(orderId).stream().map(Offer::getId).collect(Collectors.toList());
        if (!offersId.isEmpty()) {
            offerDao.deleteByIdIn(offersId);
        }
        orderDao.deleteById(orderId);
    }

    //score 1 - 10
    @Transactional
    public void registerACommentToOrder(Commend commend, int orderId) {
        Optional<Orders> ordersOptional = orderDao.findById(orderId);
        if (ordersOptional.isPresent()) {
            Orders orders = ordersOptional.get();
            Expert expert = orders.getExpert();
            expert.setScore(expert.getScore() + commend.getScore());
            expertService.updateExpert(expert);
            commendService.saveCommend(commend);
            orders.setCommend(commend);
            orderDao.save(orders);
        } else {
            throw new OrderNotFoundException();
        }
    }

    @Transactional
    public List<OrderDto> getListOrdersOfSubServiceExpert(String email) {
        Expert expert = expertService.getExpertByEmail(email);
        if (expert != null) {
            List<String> subServiceNames = expert.getServices().stream().map(SubServices::getName).collect(Collectors.toList());
            List<Orders> orders = orderDao.getListOrdersOfSubServiceExpert(subServiceNames);
            //  List<Orders> orders = orderDao.findByStateEqualsOOrStateEqualsAndSubServicesIn(OrderState.WAIT_SELECT_EXPERT, OrderState.WAIT_OFFER_EXPERTS, expert.getServices());
            return orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
        } else {
            throw new ExpertNotExistException();
        }
    }

    public List<OrderDto> getListOrdersForExpert(String email) {
        Expert expert = expertService.getExpertByEmail(email);
        if (expert != null) {
            //List<Orders> orders = orderDao.getListOrdersForExpert(expert.getId());
            List<Orders> orders = orderDao.findByExpertEquals(expert);

            return orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
        } else {
            throw new ExpertNotExistException();
        }
    }

    @Transactional
    public void updateOrderStateToPaid(int orderId) {
        Optional<Orders> orders = orderDao.findById(orderId);
        if (orders.isPresent()) {
            Orders order = orders.get();
            if (order.getCustomer().getCredit() >= order.getProposedPrice()) {
                Customer customer = order.getCustomer();
                customer.setCredit(customer.getCredit() - order.getProposedPrice());
                customerService.updateCustomer(customer);
                orderDao.updateOrderState(orderId, OrderState.PAID);
            } else {
                throw new CreditNotEnoughException();
            }
        }
    }

    @Transactional
    public int getScoreOrderForExpert(int orderId) {
        Optional<Orders> order = orderDao.findById(orderId);
        if (order.isPresent()) {
            return order.get().getCommend().getScore();
        } else {
            throw new OrderNotFoundException();
        }
    }

    @Transactional
    public OrderDto getOrderById(int id) {
        Optional<Orders> orders = orderDao.findById(id);
        if (orders.isPresent()) {
            return orderMapper.toDto(orders.get());
        } else {
            throw new OrderNotFoundException();
        }
    }

    @Transactional
    public List<OrderDto> viewListWorkOfExpert(String email) {
        Expert expert = expertService.getExpertByEmail(email);
        if (expert != null) {
            List<OrderState> workList = List.of(OrderState.WAIT_SELECT_EXPERT, OrderState.WAIT_OFFER_EXPERTS, OrderState.PAID);
            List<Orders> ordersList = orderDao.findByExpertEqualsAndStateIsNotIn(expert, workList);
            return ordersList.stream().map(orderMapper::toDto).collect(Collectors.toList());
        } else {
            throw new ExpertNotExistException();
        }
    }

    @Transactional
    public void updateOrderState(int idOrder, OrderState state) {
        orderDao.updateOrderState(idOrder, state);
    }

    @Transactional
    public List<OrderDto> getHistoryWorksOfExpert(String email) {
        Expert expert = expertService.getExpertByEmail(email);
        if (expert != null) {
            List<Orders> orders = orderDao.findByStateEqualsAndExpert(OrderState.PAID, expert);
            return orders.stream().map(orderMapper::toDto).collect(Collectors.toList());

        } else {
            throw new ExpertNotExistException();
        }
    }
}
