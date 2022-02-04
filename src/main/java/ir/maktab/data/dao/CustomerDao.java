package ir.maktab.data.dao;

import ir.maktab.data.enums.UserState;
import ir.maktab.data.model.*;
import ir.maktab.dto.ConditionSearch;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.IntFunction;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {


    Optional<Customer> findByEmailAndPassword(String email, String password);

    Optional<Customer> findByEmail(String email);

    List<Customer> findByStateEquals(UserState state);

    static Specification<Customer> selectByCondition(ConditionSearch condition) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
            if (condition.getFirstName() != null && !condition.getFirstName().equals("")) {
                predicates.add(cb.equal(root.get("firstName"), condition.getFirstName()));
            }
            if (condition.getLastName() != null && !condition.getLastName().equals("")) {
                predicates.add(cb.equal(root.get("lastName"), condition.getLastName()));
            }
            if (condition.getEmail() != null && !condition.getEmail().equals("")) {
                predicates.add(cb.equal(root.get("email"), condition.getEmail()));
            }
            if (condition.getStartDate() != null && !condition.getStartDate().equals("")) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("registerDate"), condition.getStartDate()));
            }
            if (condition.getEndDate() != null && !condition.getEndDate().equals("")) {
                predicates.add(cb.lessThanOrEqualTo(root.get("registerDate"), condition.getEndDate()));
            }
           /* if (condition.getOrderUser().equals("customer") && condition.getOrderUser() != null) {
                Join<Customer, Orders> ordersJoin = root.joinList("orders");
                // cq.multiselect(ordersJoin.get("customer.email"), cb.count(root)).groupBy(root.get("customer.email")).getOrderList().forEach(System.out::println);
                  Expression<Long> count = cb.count(ordersJoin.get("customer"));
                CriteriaQuery<Customer> having = query.select(root.get("email")).groupBy(root.get("email")).having(cb.ge(count, 0));
               predicates.add(having.getRestriction());
                // cq.orderBy(cb.desc(cb.count(ordersJoin.get("customer"))));
            }*/
           //  cq.where(predicates.toArray(new Predicate[0])).getRestriction();
            //  return query.select(root).where(predicates.toArray(new Predicate[0])).getRestriction();
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}