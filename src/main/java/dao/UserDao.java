package dao;

import config.HibernateUtil;
import model.Customer;
import model.Expert;
import model.User;
import model.enums.UserState;
import model.enums.UserType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class UserDao {
    public void save(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public User getUserByEmail(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from  User where email=:email and password=:password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        User user = null;
        try {
            user = (User) query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getListUser() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from  User ");
        List<User> users = query.list();
        transaction.commit();
        session.close();
        return users;

    }

    public List<User> getListUserNoConfirm() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from  User where state=:state");
        query.setParameter("state", UserState.NOT_CONFIRMED);
        List<User> users = query.list();
        transaction.commit();
        session.close();
        return users;

    }

    public void confirmUser(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public List<User> getListUserByCondition(UserType type, String email, String name, String family) {
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
    }

}
