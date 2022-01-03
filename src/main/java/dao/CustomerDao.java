package dao;

import config.HibernateUtil;
import model.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDao {
    public void save(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }

    public Customer getCustomerByEmailAndPass(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from  Customer where email=:email and password=:password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        List<Customer> customers = query.list();
        transaction.commit();
        session.close();
        if (!customers.isEmpty()){
            return customers.get(0);
        }else
            return null;
    }

    public int UpdatePassword(String email,String newPassword){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Query query = session.createQuery("update Customer set password=:password where email=:email ");
        query.setParameter("email", email);
        query.setParameter("password", newPassword);
        int id= query.executeUpdate();
        transaction.commit();
        session.close();
        return  id;
    }

    public Customer getCustomerByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from  Customer where email=:email");
        query.setParameter("email", email);
        List<Customer> customers = query.list();
        transaction.commit();
        session.close();
        if (!customers.isEmpty()){
           return customers.get(0);
        }else
        return null;
    }

    public void update(Customer customer){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
    }
}
