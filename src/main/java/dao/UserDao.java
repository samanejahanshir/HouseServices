package dao;

import config.HibernateUtil;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao {
    public void save(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public User getUserByEmail(String email,String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Query query = session.createQuery("from  User where email=:email and password=:password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        User user=  (User) query.getSingleResult();
        transaction.commit();
        session.close();
        return user;
    }

    public List<User> getListUser(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Query query = session.createQuery("from  User ");
        List<User> users=query.list();
        transaction.commit();
        session.close();
        return users;

    }
}
