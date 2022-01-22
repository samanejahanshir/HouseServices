package ir.maktab.data.dao;

import ir.maktab.data.enums.UserType;
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
public interface UserDao extends JpaRepository<User, Integer> , JpaSpecificationExecutor<User> {

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

   /* public List<User> getListUserByCondition(UserType type, String email, String name, String family) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria;
        if (type.equals(UserType.CUSTOMER)) {
            criteria = session.createCriteria(Customer.class);
        } else if (type.equals(UserType.EXPERT)) {
            criteria = session.createCriteria(Expert.class);
        } else {
            criteria = session.createCriteria(User.class);
        }
        if (email != null && !email.equals("")) {
            criteria.add(Restrictions.eq("email", email));
        }
        if (name != null && !name.equals("")) {
            criteria.add(Restrictions.eq("firstName", name));
        }
        if (family != null && !family.equals("")) {
            criteria.add(Restrictions.eq("lastName", family));
        }
        List<User> users = criteria.list();
        transaction.commit();
        session.close();
        return users;
    }*/
   static Specification<User> selectByCondition(ConditionSearch condition) {
       return (root, cq, cb) -> {
           List<Predicate> predicates = new ArrayList<>();
           if (condition.getFirstName() != null && !condition.getFirstName().equals("")) {
               predicates.add(cb.equal(root.get("firstName"),condition.getFirstName()));
           }
           if (condition.getLastName()!=null && !condition.getLastName().equals("")) {
               predicates.add(cb.equal(root.get("lastName"),condition.getLastName()));
           }
           if (condition.getEmail()!=null && !condition.getEmail().equals("")) {
               predicates.add(cb.equal(root.get("email"), condition.getEmail()));
           }
           if (condition.getRole() != null ) {
               predicates.add(cb.equal(root.get("role"),condition.getRole()));
           }
           return cb.and(predicates.toArray(new Predicate[0]));
       };
   }
}
