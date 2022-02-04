package ir.maktab.data.dao;

import ir.maktab.data.enums.OrderState;
import ir.maktab.data.model.*;
import ir.maktab.dto.ConditionSearch;
import ir.maktab.dto.OrdersSearch;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public interface OrderDao extends JpaRepository<Orders, Integer>, JpaSpecificationExecutor<Orders> {

    @Query(value = "select o from Orders o inner  join o.subServices s where s.name in :list and o.state='WAIT_OFFER_EXPERTS' or o.state='WAIT_SELECT_EXPERT'")
    List<Orders> getListOrdersOfSubServiceExpert(@Param("list") List<String> subServices);

    List<Orders> findByStateEqualsAndSubServicesIn(OrderState state,List<SubServices> subServices);
    List<Orders> findByExpertEquals(Expert expert);

    List<Orders> findByExpertEqualsAndStateIsNotIn(Expert expert, List<OrderState> state);

    @Modifying
    @Query(value = "update Orders o set o.state=:state where o.id=:id")
    void updateOrderState(@Param("id") int idOrder, @Param("state") OrderState state);

    List<Orders> findByCustomer_Id(@Param("id") int customerId);

    @Query(value = "select o from  Customer c inner join c.orders o  where o.customer.id=:id and o.state <> 'PAID'")
    List<Orders> findByCustomer_IdAndStateNotLike(@Param("id") int customerId);

    @Query(value = "select o from  Customer c inner join c.orders o  where o.customer.id=:id and o.state <> 'PAID'")
    List<Orders> getListOrdersThatNotFinished(@Param("id") int customerId);

    List<Orders> findByStateEqualsAndExpert(OrderState state, Expert expert);

    static Specification<Orders> selectByCondition(OrdersSearch ordersSearch) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (ordersSearch.getState() != null) {
                predicates.add(cb.equal(root.get("state"), ordersSearch.getState()));
            }
            if (ordersSearch.getSubServiceName() != null && !ordersSearch.getSubServiceName().equals("")) {
                Join<Orders, SubServices> serviceJoin = root.join("subServices");
                predicates.add(cb.equal(serviceJoin.get("name"), ordersSearch.getSubServiceName()));
            }
            if (ordersSearch.getMainServiceName() != null && !ordersSearch.getMainServiceName().equals("")) {
                Join<Orders, SubServices> serviceJoin = root.join("subServices");
                Join<SubServices, MainServices> mainServices = serviceJoin.join("mainServices");
                predicates.add(cb.equal(mainServices.get("groupName"), ordersSearch.getMainServiceName()));
            }
            if (ordersSearch.getStartDate() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("orderDoingDate"), ordersSearch.getStartDate()));
            }
            if (ordersSearch.getEndDate() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("orderDoingDate"), ordersSearch.getEndDate()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
