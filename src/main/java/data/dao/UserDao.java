package data.dao;

import data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

    @org.springframework.data.jpa.repository.Query(value = "from  User where state='NOT_CONFIRMED'")
    List<User> getListUserNoConfirm();

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
}
