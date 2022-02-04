package ir.maktab.data.dao;

import ir.maktab.data.enums.UserState;
import ir.maktab.data.enums.UserType;
import ir.maktab.data.model.Customer;
import ir.maktab.data.model.Expert;
import ir.maktab.data.model.SubServices;
import ir.maktab.data.model.User;
import ir.maktab.dto.ConditionSearch;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

    List<User> findByStateEquals(UserState state);

    static Specification<User> selectByCondition(ConditionSearch condition) {
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
            if (condition.getRole() != null) {
                predicates.add(cb.equal(root.get("role"), condition.getRole()));
            }
            if (condition.getStartDate() != null && !condition.getStartDate().equals("")) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("registerDate"), condition.getStartDate()));
            }
            if (condition.getEndDate() != null && !condition.getEndDate().equals("")) {
                predicates.add(cb.lessThanOrEqualTo(root.get("registerDate"), condition.getEndDate()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
