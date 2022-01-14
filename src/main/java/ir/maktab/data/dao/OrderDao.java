package ir.maktab.data.dao;

import ir.maktab.data.enums.OrderState;
import ir.maktab.data.model.Offer;
import ir.maktab.data.model.Orders;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Orders, Integer> {

    @Query(value = "select o from Orders o inner  join o.subServices s where s.name in :list and o.state='WAIT_OFFER_EXPERTS' or o.state='WAIT_SELECT_EXPERT'")
    List<Orders> getListOrdersOfSubServiceExpert(@Param("list") List<String> subServices);

    @Query(value = "select o from Offer o inner join o.expert e inner  join e.services s where o.orders.id=:id")
    List<Offer> getListOffers(@Param("id") int ordersId);

    @Query(value = "select o from Offer o inner join o.expert e inner  join e.services s where o.orders.id=:id")
    List<Offer> getListOffersBySort(@Param("id") int ordersId, Sort sort);

    @Query(value = "from Orders o where o.expert.id=:id")
    List<Orders> getListOrdersForExpert(@Param("id") int expertId);

    @Modifying
    @Query(value = "update Orders o set o.state=:state where o.id=:id")
    int updateOrderState(@Param("id") int idOrder, @Param("state") OrderState state);

    @Modifying
    @Query(value = "update Orders o set o.Comment=:comment where o.id=:id")
    int updateOrderComment(@Param("id") int orderId, @Param("comment") String comment);

    List<Orders> findByCustomer_Id(@Param("id") int customerId);

    @Query(value = "select o from  Customer c inner join c.orders o  where o.customer.id=:id and o.state <> 'PAID'")
    List<Orders> findByCustomer_IdAndStateNotLike(@Param("id") int customerId);
}
