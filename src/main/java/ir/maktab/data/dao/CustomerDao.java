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

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {


    Optional<Customer> findByEmailAndPassword(String email, String password);

    Optional<Customer> findByEmail(String email);

   /* @Query(value = "from Customer c  where c.email=:email")
    Optional<Customer> getCustomerByEmail(@Param("email") String email);*/

    List<Customer> findByStateEquals(UserState state);

    static Specification<Customer> selectByCondition(ConditionSearch condition) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
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
           /* if (!condition.getOrderUser().equals("") && condition.getOrderUser() != null) {
                Join<Customer, Orders> ordersJoin = root.joinList("orders");
               cq.multiselect(ordersJoin.get("email"), cb.count(root)).groupBy(root.get("email")).getOrderList().forEach(System.out::println);

            }*/
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}